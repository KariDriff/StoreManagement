package com.quatrocentosquatro.storemanagement.controller;

// Importações necessárias
import java.util.ArrayList;
import java.util.List;
import com.quatrocentosquatro.storemanagement.model.Administrador;
import com.quatrocentosquatro.storemanagement.model.Funcionario;
import com.quatrocentosquatro.storemanagement.model.Usuario;
/**
 * Classe responsável por gerenciar usuários do sistema, incluindo
 * funcionalidades para adicionar, listar, buscar, atualizar e remover usuários.
 * 
 */

public class GerenciarUsuarios {
    private final List<Usuario> usuarios = new ArrayList<>();
    private int nextId = 1;

    public static void main(String[] args) {
        GerenciarUsuarios gerenciarUsuarios = new GerenciarUsuarios();
        gerenciarUsuarios.adicionarAdministrador("Admin", "adm", "1234");
    }

    /**
     * Adiciona um novo funcionário ao sistema.
     *
     * @param nome Nome do funcionário.
     * @param login Login do funcionário.
     * @param senha Senha do funcionário.
     * @param cargo Cargo do funcionário.
     */
    public void adicionarFuncionario(String nome, String login, String senha, String cargo) {
        usuarios.add(new Funcionario(nextId++, nome, login, senha, cargo));
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
    }

    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return Lista de usuários.
     */
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    /**
     * Busca um usuário pelo ID.
     *
     * @param id ID do usuário.
     * @return Usuário correspondente ao ID ou null se não encontrado.
     */
    public Usuario buscarPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param id ID do usuário a ser atualizado.
     * @param novoNome Novo nome do usuário.
     * @param novoLogin Novo login do usuário.
     * @param novaSenha Nova senha do usuário.
     */
    public void atualizarUsuario(int id, String novoNome, String novoLogin, String novaSenha) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setNome(novoNome);
            u.setLogin(novoLogin);
            u.setSenha(novaSenha);
        }
    }

    /**
     * Remove um usuário pelo ID.
     *
     * @param id ID do usuário a ser removido.
     */
    public void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }

/**
 * Autentica um usuário com base no login e senha fornecidos.
 * @param login O login do usuário.
 * @param senha A senha do usuário.
 * @return O usuário autenticado se encontrado, ou null se não houver correspondência.
 * */
    public Usuario autenticar(String login, String senha) {
    return usuarios.stream()
        .filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha))
        .findFirst()
        .orElse(null);
}
}
