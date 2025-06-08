package com.quatrocentosquatro.storemanagement.model;

import java.io.Serializable; // Importando Serializable para permitir a serialização da classe

/**
 * Classe que representa um fornecedor no sistema.
 * Contém informações como ID, nome, telefone, email, CNPJ e status de atividade.
 * 
 * @author João M. Chervinski
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
     * @param id       (int)    - ID do fornecedor.
     * @param nome     (String) - Nome do fornecedor.
     * @param telefone (String) - Telefone do fornecedor.
     * @param email    (String) - Email do fornecedor.
     * @param cnpj     (String) - CNPJ do fornecedor.
     */
    public Fornecedor() {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.isAtivo = true; // Define o fornecedor como ativo por padrão ao ser criado.
    }


    /* Getters e Setters */

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}

    public boolean isAtivo() {return isAtivo;}
    public void setAtivo(boolean ativo) {isAtivo = ativo;}
}