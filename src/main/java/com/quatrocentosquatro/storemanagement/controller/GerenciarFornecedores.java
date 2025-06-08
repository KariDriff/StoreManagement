package com.quatrocentosquatro.storemanagement.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.Fornecedor;

/**
 * Controller para gerenciar fornecedores.
 * Permite adicionar, listar, buscar, atualizar e remover fornecedores.
 * 
 * @author João M. Chervinski
 * @author Kaio A. Souza
 */
public class GerenciarFornecedores {
    private List<Fornecedor> fornecedores; // Lista que armazena os fornecedores
    private final String ARQUIVO = "src/main/java/com/quatrocentosquatro/storemanagement/database/fornecedores.db"; // Caminho do arquivo onde os fornecedores serão salvos
    private int nextId = 1; // Próximo ID a ser atribuído aos fornecedores
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/Fornecedores.log";
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * <p> Construtor da classe GerenciarFornecedores.
     * <p> Inicializa a lista de fornecedores e carrega os dados do arquivo.
     */
    public GerenciarFornecedores() {
        fornecedores = carregarFornecedores();
        nextId = fornecedores.stream()
                             .mapToInt(Fornecedor::getId)
                             .max()
                             .orElse(0) + 1;
    }

    /**
     * <p> Método para salvar a lista de fornecedores em um arquivo.
     * <p> Utiliza serialização para armazenar os objetos de fornecedores.
     */
    private void salvarFornecedores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(fornecedores);
        } catch (IOException e) {
            System.out.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
        String log = "[" + agora() + "] A lista de fornecedores foi salva.";
        registrarOperacoes(log);
    }

    /** 
     * <p> Método para carregar a lista de fornecedores de um arquivo.
     * <p> Utiliza deserialização para recuperar os objetos de fornecedores.
     * 
     * @return A lista de fornecedores.
     */
    @SuppressWarnings("unchecked")
    private List<Fornecedor> carregarFornecedores() {
        File file = new File(ARQUIVO);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Fornecedor>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
     */
    private String agora() {return LocalDateTime.now().format(formataHora);}

    /**
     * Adiciona um novo fornecedor à lista.
     *
     * @param nome     (String) - Nome do fornecedor.
     * @param telefone (String) - Telefone do fornecedor.
     * @param email    (String) - Email do fornecedor.
     * @param cnpj     (String) - CNPJ do fornecedor.
     */
    public void adicionarFornecedor(String nome, String telefone, String email, String cnpj) {
        fornecedores.add(new Fornecedor());

        String log = "[" + agora() + "] Fornecedor de ID " + nextId + " (" + nome + ") foi adicionado.";
        registrarOperacoes(log);
        salvarFornecedores();
    }

    /**
     * Lista todos os fornecedores cadastrados.
     *
     * @return Lista de fornecedores.
     */
    public List<Fornecedor> listarFornecedores() {return fornecedores;}

    /**
     * Busca um fornecedor pelo ID.
     *
     * @param id (int) - ID do fornecedor.
     * 
     * @return Fornecedor correspondente ao ID ou null se não encontrado.
     */
    public Fornecedor buscarPorId(int id) {
        return fornecedores.stream()
                           .filter(f -> f.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    /**
     * Atualiza as informações de um fornecedor existente.
     *
     * @param id           (int)    - ID do fornecedor a ser atualizado.
     * @param novoNome     (String) - Novo nome do fornecedor.
     * @param novoTelefone (String) - Novo telefone do fornecedor.
     * @param novoEmail    (String) - Novo email do fornecedor.
     * @param novoCnpj     (String) - Novo CNPJ do fornecedor.
     */
    public void atualizarFornecedor(int id, String novoNome, String novoTelefone, String novoEmail, String novoCnpj) {
        Fornecedor f = buscarPorId(id);
        if (f != null) {
            f.setNome(novoNome);
            f.setTelefone(novoTelefone);
            f.setEmail(novoEmail);
            f.setCnpj(novoCnpj);
            salvarFornecedores();
        }

        String log = "[" + agora() + "] Fornecedor de ID " + id + " teve seu registro atualizado.";
        registrarOperacoes(log);
    }

    /**
     * Remove um fornecedor pelo ID.
     *
     * @param id ID do fornecedor a ser removido.
     */
    public void removerFornecedor(int id) {
        fornecedores.removeIf(f -> f.getId() == id);

        String log = "[" + agora() + "] Fornecedor de ID " + id + " foi removido.";
        registrarOperacoes(log);
        salvarFornecedores();
    }

    /**
     * Registra as operações feitas na classe em seu log.
     * 
     * @param acao (String) - A operação feita.
     */
    private void registrarOperacoes(String acao) {
        try {
            FileWriter escreverAcao = new FileWriter(caminhoLog, true);
            escreverAcao.append(acao).append("\n");
            escreverAcao.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível registrar ação no Gerenciar Usuários:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
