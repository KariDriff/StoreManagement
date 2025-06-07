package com.quatrocentosquatro.storemanagement.controller;

import java.io.FileWriter;
import java.io.IOException;
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
    private final List<Usuario> usuarios = new ArrayList<>();
    private int nextId = 1;
    private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/Usuarios.log";
    private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public static void main(String[] args) {
        GerenciarUsuarios gerenciarUsuarios = new GerenciarUsuarios();
        gerenciarUsuarios.adicionarAdministrador("Admin", "adm", "1234");
    }

    /**
     * Usado para puxar a data e horário atual do sistema.
     * 
     * @return A data e hora atual no formato dd/MM, hh:mm:ss
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
    }

    /**
     * Adiciona um novo administrador ao sistema.
     *
     * @param nome Nome do administrador.
     * @param login Login do administrador.
     * @param senha Senha do administrador.
     */
    public void adicionarAdministrador(String nome, String login, String senha) {
        usuarios.add(new Administrador(nextId++, nome, login, senha));

        String log = "[" + agora() + "] Usuário de ID " + nextId + " (" + nome + ") foi adicionado como administrador.";
        registrarOperacoes(log);
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
    }

    /**
     * Autentica um usuário com base no login e senha fornecidos.
     * 
     * @param login (Strin) - O login do usuário.
     * @param senha (String) - A senha do usuário.
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
