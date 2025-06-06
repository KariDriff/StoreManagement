package com.quatrocentosquatro.storemanagement.controller;

import com.quatrocentosquatro.storemanagement.model.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class GerenciarFornecedores {
    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private int nextId = 1;

    public void adicionarFornecedor(String nome, String telefone, String email, String cnpj) {
        fornecedores.add(new Fornecedor(nextId++, nome, telefone, email, cnpj));
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedores;
    }

    public Fornecedor buscarPorId(int id) {
        return fornecedores.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public void atualizarFornecedor(int id, String novoNome, String novoTelefone, String novoEmail, String novoCnpj) {
        Fornecedor f = buscarPorId(id);
        if (f != null) {
            f.setNome(novoNome);
            f.setTelefone(novoTelefone);
            f.setEmail(novoEmail);
            f.setCnpj(novoCnpj);
        }
    }

    public void removerFornecedor(int id) {
        fornecedores.removeIf(f -> f.getId() == id);
    }
}
