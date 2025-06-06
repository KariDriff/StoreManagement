package com.quatrocentosquatro.storemanagement.controller;

// Importações necessárias
import com.quatrocentosquatro.storemanagement.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller para gerenciar fornecedores.
 * Permite adicionar, listar, buscar, atualizar e remover fornecedores.
 */
public class GerenciarFornecedores {
    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private int nextId = 1;

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
        }
    }

    /**
     * Remove um fornecedor pelo ID.
     *
     * @param id ID do fornecedor a ser removido.
     */
    public void removerFornecedor(int id) {
        fornecedores.removeIf(f -> f.getId() == id);
    }
}
