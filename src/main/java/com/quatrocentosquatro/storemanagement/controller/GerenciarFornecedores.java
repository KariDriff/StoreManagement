package com.quatrocentosquatro.storemanagement.controller;

// Importações necessárias
import com.quatrocentosquatro.storemanagement.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Controller para gerenciar fornecedores.
 * Permite adicionar, listar, buscar, atualizar e remover fornecedores.
 */
public class GerenciarFornecedores {
    private List<Fornecedor> fornecedores; // Lista que armazena os fornecedores
    private final String ARQUIVO = "fornecedores.db"; // Caminho do arquivo onde os fornecedores serão salvos
    private int nextId = 1; // Próximo ID a ser atribuído aos fornecedores

    /**
     * Construtor da classe GerenciarFornecedores.
     * Inicializa a lista de fornecedores e carrega os dados do arquivo.
     */
    public GerenciarFornecedores() {
    fornecedores = carregarFornecedores();
    nextId = fornecedores.stream()
                         .mapToInt(Fornecedor::getId)
                         .max()
                         .orElse(0) + 1;
    }

    /**
     * Método para salvar a lista de fornecedores em um arquivo.
     * Utiliza serialização para armazenar os objetos de fornecedores.
     */
    private void salvarFornecedores() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
        out.writeObject(fornecedores);
    } catch (IOException e) {
        System.out.println("Erro ao salvar fornecedores: " + e.getMessage());
    }
    }

    // Método para carregar a lista de fornecedores de um arquivo.
    // Utiliza deserialização para recuperar os objetos de fornecedores.
    @SuppressWarnings("unchecked")
    private List<Fornecedor> carregarFornecedores() {
    File file = new File(ARQUIVO);
    if (!file.exists()) return new ArrayList<>();
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
        return (List<Fornecedor>) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
        return new ArrayList<>();
    }
    }


    /**
     * Adiciona um novo fornecedor à lista.
     *
     * @param nome Nome do fornecedor.
     * @param telefone Telefone do fornecedor.
     * @param email Email do fornecedor.
     * @param cnpj CNPJ do fornecedor.
     */
    public void adicionarFornecedor(String nome, String telefone, String email, String cnpj) {
        fornecedores.add(new Fornecedor(nextId++, nome, telefone, email, cnpj));
        salvarFornecedores();
    }

    /**
     * Lista todos os fornecedores cadastrados.
     *
     * @return Lista de fornecedores.
     */
    public List<Fornecedor> listarFornecedores() {
        return fornecedores;
    }

    /**
     * Busca um fornecedor pelo ID.
     *
     * @param id ID do fornecedor.
     * @return Fornecedor correspondente ao ID ou null se não encontrado.
     */
    public Fornecedor buscarPorId(int id) {
        return fornecedores.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    /**
     * Atualiza as informações de um fornecedor existente.
     *
     * @param id ID do fornecedor a ser atualizado.
     * @param novoNome Novo nome do fornecedor.
     * @param novoTelefone Novo telefone do fornecedor.
     * @param novoEmail Novo email do fornecedor.
     * @param novoCnpj Novo CNPJ do fornecedor.
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
    }

    /**
     * Remove um fornecedor pelo ID.
     *
     * @param id ID do fornecedor a ser removido.
     */
    public void removerFornecedor(int id) {
        fornecedores.removeIf(f -> f.getId() == id);
        salvarFornecedores();
    }
}
