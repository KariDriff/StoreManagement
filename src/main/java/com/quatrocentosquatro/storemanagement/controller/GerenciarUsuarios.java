package com.quatrocentosquatro.storemanagement.controller;

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

    public void adicionarFuncionario(String nome, String login, String senha, String cargo) {
        usuarios.add(new Funcionario(nextId++, nome, login, senha, cargo));
    }

    public void adicionarAdministrador(String nome, String login, String senha) {
        usuarios.add(new Administrador(nextId++, nome, login, senha));
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public void atualizarUsuario(int id, String novoNome, String novoLogin, String novaSenha) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setNome(novoNome);
            u.setLogin(novoLogin);
            u.setSenha(novaSenha);
        }
    }

    public void removerUsuario(int id) {
        usuarios.removeIf(u -> u.getId() == id);
    }

    public Usuario autenticar(String login, String senha) {
    return usuarios.stream()
        .filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha))
        .findFirst()
        .orElse(null);
}
}
