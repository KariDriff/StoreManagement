package com.quatrocentosquatro.storemanagement.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.Administrador;
import com.quatrocentosquatro.storemanagement.model.Funcionario;
import com.quatrocentosquatro.storemanagement.model.Usuario;

/**
 * Classe responsável por gerenciar usuários do sistema, incluindo
 * funcionalidades para adicionar, listar, buscar, atualizar e remover usuários.
 * 
 * @author João M. Chervinski
 * @author Kaio A. Souza
 */
public class GerenciarUsuarios {
    private List<Usuario> usuarios;
    private int nextId = 1;
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/Usuarios.log";
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String ARQUIVO = "src/main/java/com/quatrocentosquatro/storemanagement/database/usuario.db";

    /**
     * Construtor da classe GerenciarUsuarios.
     * Inicializa a lista de usuários e carrega os dados do arquivo.
     */
    public GerenciarUsuarios() {
        usuarios = carregarUsuarios();
        nextId = usuarios.stream()
                         .mapToInt(Usuario::getId)
                         .max()
                         .orElse(0) + 1;
    }

    /**
     * <p> Método para salvar a lista de usuários em um arquivo.
     * <p> Utiliza serialização para armazenar os objetos de usuários.
     */
    private void salvarUsuarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
        String log = "[" + agora() + "] A lista de usuários foi salva.";
        registrarOperacoes(log);
    }

    /**
     * <p> Método para carregar a lista de usuários de um arquivo.
     * <p> Utiliza deserialização para recuperar os objetos de usuários.
     * 
     * @return A lista de usuários.
     */
    @SuppressWarnings("unchecked")
    private List<Usuario> carregarUsuarios() {
        File file = new File(ARQUIVO);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        GerenciarUsuarios gerenciarUsuarios = new GerenciarUsuarios();
        gerenciarUsuarios.adicionarAdministrador("Admin", "adm", "1234");
    }

    /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
     */
    private String agora() {return LocalDateTime.now().format(formataHora);}

    /**
     * Adiciona um novo funcionário ao sistema.
     *
     * @param nome  (String) - Nome do funcionário.
     * @param login (String) - Login do funcionário.
     * @param senha (String) - Senha do funcionário.
     * @param cargo (String) - Cargo do funcionário.
     */
    public void adicionarFuncionario(String nome, String login, String senha, String cargo) {
        usuarios.add(new Funcionario(nextId++, nome, login, senha, cargo));

        String log = "[" + agora() + "] Usuário de ID " + nextId + " (" + nome + ") foi adicionado como funcionario.";
        registrarOperacoes(log);
        salvarUsuarios();
    }

    /**
     * Adiciona um novo administrador ao sistema.
     *
     * @param nome  (String) - Nome do administrador.
     * @param login (String) - Login do administrador.
     * @param senha (String) - Senha do administrador.
     */
    public void adicionarAdministrador(String nome, String login, String senha) {
        usuarios.add(new Administrador(nextId++, nome, login, senha));

        String log = "[" + agora() + "] Usuário de ID " + nextId + " (" + nome + ") foi adicionado como administrador.";
        registrarOperacoes(log);
        salvarUsuarios();
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return Lista de usuários.
     */
    public List<Usuario> listarUsuarios() {return usuarios;}

    /**
     * Busca um usuário pelo ID.
     *
     * @param id (int) - ID do usuário.
     * 
     * @return Usuário correspondente ao ID ou null se não encontrado.
     */
    public Usuario buscarPorId(int id) {return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);}

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param id        (int)    - ID do usuário a ser atualizado.
     * @param novoNome  (String) - Novo nome do usuário.
     * @param novoLogin (String) - Novo login do usuário.
     * @param novaSenha (String) - Nova senha do usuário.
     */
    public void atualizarUsuario(int id, String novoNome, String novoLogin, String novaSenha) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setNome(novoNome);
            u.setLogin(novoLogin);
            u.setSenha(novaSenha);
        }

        String log = "[" + agora() + "] Usuário de ID " + id + " teve seu registro atualizado";
        registrarOperacoes(log);
        salvarUsuarios();
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id (int) - ID do usuário a ser removido.
     */
    public void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);

        String log = "[" + agora() + "] Usuário de ID " + id + " foi removido";
        registrarOperacoes(log);
        salvarUsuarios();
    }

    /**
     * Autentica um usuário com base no login e senha fornecidos.
     * 
     * @param login (Strin) - O login do usuário.
     * @param senha (String) - A senha do usuário.
     * 
     * @return O usuário autenticado se encontrado, ou null se não houver correspondência.
     */
    public Usuario autenticar(String login, String senha) {
        String log = "[" + agora() + "] Usuário de login \" " + login + " \" e senha \" " + senha + " \" foi autenticado.";
        registrarOperacoes(log);

        return usuarios.stream().filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha)).findFirst().orElse(null);
    }

    /**
     * Registra as operações feitas na classe em seu log
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
