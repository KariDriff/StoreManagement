package com.quatrocentosquatro.storemanagement.model;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Classe para salvar e ler despesas.
 * 
 * @author Kaio A. Souza
 */
public class Despesa {
  private int id;
  private float valor;
  private boolean isPago;
  private String descricao;
  private final String caminhoDb = "src/main/java/com/quatrocentosquatro/storemanagement/database/despesas.db";

  /**
   * Construtor da classe {@code Despesa}.
   * @param id        (int)     - O ID da despesa
   * @param valor     (float)   - O valor da despesa, em BRL.
   * @param isPago    (boolean) - Se a despesa está paga.
   * @param descricao (String)  - A descrição da despesa.
   */
  public Despesa(int id, float valor, boolean isPago, String descricao) {
    this.id = id;
    this.valor = valor;
    this.isPago = isPago;
    this.descricao = descricao;
  }

  /**
   * Construtor vazio da classe Despesa.
   */
  public Despesa() {}

  /**
   * Busca e retorna uma despesa o ID fornecido.
   * 
   * @param id (int) - O ID da despesa que quer buscar.
   * 
   * @return Um objeto Despesa com o ID correspondente. Caso contrário, retorna null 
   */
  public Despesa buscarPorId(int id) {
    try {
      File arqvDesp = new File(caminhoDb);
      Scanner analArqvDesp = new Scanner(arqvDesp);

      while (analArqvDesp.hasNext()) {
        String linha = analArqvDesp.nextLine();
        String[] partes = linha.split("\\|");

        if (Integer.parseInt(partes[0]) == id) {
          analArqvDesp.close();

          return new Despesa(Integer.parseInt(partes[0]), Float.parseFloat(partes[1]), Boolean.parseBoolean(partes[2]), partes[3]);
        }
      }
      analArqvDesp.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Não foi possível buscar a despesa:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    return null; // Retorna null se não encontrar
  }

  /**
   * Adiciona uma despesa e a registra num arquivo com um ID.
   * 
   * @param valor     (float)   - O valor da despesa.
   * @param isPago    (boolean) - Se a despesa está paga.
   * @param descricao (String)  - A descrição da despesa.
   * 
   * @return {@code true} se o registro foi um sucesso e {@code false} se resultou em erro.
   */
  public boolean adicionarDespesa(float valor, boolean isPago, String descricao) {
    try {
      File arqvDesp = new File(caminhoDb);
      int nextId = 0;

      // Se o arquivo já existe, encontra o próximo ID lendo a última linha
      if (arqvDesp.exists()) {
        Scanner analArqv = new Scanner(arqvDesp);
        String linha = "";

        while (analArqv.hasNext()) {linha = analArqv.nextLine();}
        analArqv.close();
        if (!linha.isEmpty()) {
          String[] partes = linha.split("\\|");
          nextId = Integer.parseInt(partes[0]) + 1;
        }
      }

      FileWriter escrDesp = new FileWriter(caminhoDb, true);
      escrDesp.append(nextId + "|" + valor + "|" + isPago + "|" + descricao + "\n");
      escrDesp.close();

      return true;
    } catch (Exception e) {
      // Exibe mensagem de erro em caso de falha
      JOptionPane.showMessageDialog(null, "Não foi possível registrar saída:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
  }

  /**
   * Lê o arquivo de despesas e retorna todas as despesas.
   */
  public void listarDespesas() {
    NumberFormat formataReal = new DecimalFormat("R$ ###,##0.00");
    ArrayList<Despesa> despesas = new ArrayList<Despesa>();
    String linha = "";
    String[] partes;
    
    try {
      File arqvDesp = new File(caminhoDb);
      if (!arqvDesp.exists()) {return;} // Termina caso o arquivo não exista

      Scanner analArqvDesp = new Scanner(arqvDesp);
  
      while (analArqvDesp.hasNext()) {
        linha = analArqvDesp.nextLine();
        partes = linha.split("\\|");
        if (!linha.isEmpty()) {despesas.add(new Despesa(Integer.parseInt(partes[0]), Float.parseFloat(partes[1]), Boolean.parseBoolean(partes[2]), partes[3]));}
      }

      analArqvDesp.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Não foi possível listar despesas:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    if (despesas.isEmpty()) {
      System.out.println("Não há despesas");
      return;
    } else {
      // TODO Ordena a lista em false e true, nesta ordem. Requer testes.
      despesas.sort((d1, d2) -> Boolean.compare(d1.getIsPago(), d2.getIsPago()));

      // Lista os itens.
      for (int i = 0; i < despesas.size(); i++) {
        System.out.println("ID: " + despesas.get(i).getId());
        System.out.println("Valor: " + formataReal.format(despesas.get(i).getValor()));
        System.out.println("Despesa está paga? " + despesas.get(i).getIsPago());
        System.out.println("Descrição:");
        System.out.println(despesas.get(i).getDescricao());
        System.out.println("-----------------------------------------");
      }
    }
  }

  /* Getters e Setters */

  public int getId() {return this.id;}
  public void setId(int id) {this.id = id;}

  public float getValor() {return this.valor;}
  public void setValor(float valor) {this.valor = valor;}

  public boolean getIsPago() {return this.isPago;}
  public void setIsPago(boolean isPago) {this.isPago = isPago;}
  
  public String getDescricao() {return this.descricao;}
  public void setDescricao(String descricao) {this.descricao = descricao;}
}
