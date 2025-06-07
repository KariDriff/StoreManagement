package com.quatrocentosquatro.storemanagement.model;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Despesa {
  private int id;
  private float valor;
  private boolean isPago;
  private String descricao;


  public Despesa(int id, float valor, boolean isPago, String descricao) {
    this.id = id;
    this.valor = valor;
    this.isPago = isPago;
    this.descricao = descricao;
  }

  public Despesa() {}

  /**
   * Busca e retorna uma despesa o ID fornecido.
   * 
   * @param id (int) - O ID da despesa que quer buscar.
   * @return Um objeto Despesa com o ID correspondente.
   */
  public Despesa buscarPorId(int id) {
    String[] partes = new String[4];
    String linha = "";

    try {
      File arqvDesp = new File("src/main/java/com/quatrocentosquatro/storemanagement/database/despesas.db");
      Scanner analArqvDesp = new Scanner(arqvDesp);
  
      while (analArqvDesp.hasNext()) {
        linha = analArqvDesp.nextLine();
        partes = linha.split("\\|");
  
        if (Integer.parseInt(partes[0]) == id) {
          break;
        }
      }
  
      analArqvDesp.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Não foi possível buscar a despesa:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    return new Despesa(Integer.parseInt(partes[0]), Float.parseFloat(partes[1]), Boolean.parseBoolean(partes[2]), partes[3]);
  }

  public boolean adicionarDespesa(float valor, boolean isPago, String descricao) {
    try {
      FileWriter escrDesp = new FileWriter("src/main/java/com/quatrocentosquatro/storemanagement/database/despesas.db", true);
      File arqvDesp = new File("src/main/java/com/quatrocentosquatro/storemanagement/database/despesas.db");

      int nextId;

      // Lê o arquivo para pegar o ID da última linha e incrementar para o próximo registro.
      if (!arqvDesp.exists()) {nextId = 0;} // Caso o arquivo não exista, ID é 0.
      else {
        Scanner analArqv = new Scanner(arqvDesp);
        String linha = "";

        while (analArqv.hasNext()) {linha = analArqv.nextLine();}
        analArqv.close();

        // Pega a linha, divide-a em partes e pega a primeira parte pra puxar o ID.
        if (!linha.isEmpty()) {
          String[] partes = linha.split("\\|");

          nextId = Integer.parseInt(partes[0]);
        } else {nextId = 0;}
      }

      this.isPago = false;

      // Incrementa o ID apenas se o arquivo não estiver vazio
      if(!arqvDesp.toString().isEmpty()) {nextId++;}

      escrDesp.append(nextId + "|" + valor + "|" + isPago + "|" + descricao);
      escrDesp.close();

      return true;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Não foi possível registrar saída:\n" + e.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);

      return false;
    }
  }

  public int getId() {return this.id;}
  public void setId(int id) {this.id = id;}

  public float getValor() {return this.valor;}
  public void setValor(float valor) {this.valor = valor;}

  public boolean getIsPago() {return this.isPago;}
  public void setIsPago(boolean isPago) {this.isPago = isPago;}
  
  public String getDescricao() {return this.descricao;}
  public void setDescricao(String descricao) {this.descricao = descricao;}

}
