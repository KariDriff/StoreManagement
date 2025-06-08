package com.quatrocentosquatro.storemanagement.model;
import java.io.Serializable; // Importando Serializable para permitir a serialização da classe

/**
 * Classe que representa um fornecedor no sistema.
 * Contém informações como ID, nome, telefone, email, CNPJ e status de atividade.
 */
public class Fornecedor implements Serializable { // Implementa Serializable para permitir a serialização de objetos desta classe
    private static final long serialVersionUID = 1L; // Versão de serialização para compatibilidade

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private boolean isAtivo;

    /**
     * Construtor para criar um novo fornecedor.
     *
     * @param id       ID do fornecedor.
     * @param nome     Nome do fornecedor.
     * @param telefone Telefone do fornecedor.
     * @param email    Email do fornecedor.
     * @param cnpj     CNPJ do fornecedor.
     */
    public Fornecedor(int id, String nome, String telefone, String email, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.isAtivo = true; // Define o fornecedor como ativo por padrão ao ser criado.
    }

    /**
     * Retorna o ID do fornecedor.
     *
     * @return O ID do fornecedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do fornecedor.
     *
     * @param id O novo ID do fornecedor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do fornecedor.
     *
     * @return O nome do fornecedor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do fornecedor.
     *
     * @param nome O novo nome do fornecedor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone do fornecedor.
     *
     * @return O telefone do fornecedor.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do fornecedor.
     *
     * @param telefone O novo telefone do fornecedor.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o email do fornecedor.
     *
     * @return O email do fornecedor.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do fornecedor.
     *
     * @param email O novo email do fornecedor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o CNPJ do fornecedor.
     *
     * @return O CNPJ do fornecedor.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ do fornecedor.
     *
     * @param cnpj O novo CNPJ do fornecedor.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Retorna o status de atividade do fornecedor.
     *
     * @return true se o fornecedor está ativo, false caso contrário.
     */
    public boolean isAtivo() {
        return isAtivo;
    }

    /**
     * Define o status de atividade do fornecedor.
     *
     * @param ativo O novo status de atividade do fornecedor.
     */
    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }
}