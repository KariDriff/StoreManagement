package com.quatrocentosquatro.storemanagement.model;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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

  public void listarDespesas() {
    NumberFormat formataReal = new DecimalFormat("R$ ###,##0.00");
    ArrayList<Despesa> despesas = new ArrayList<Despesa>();
    String linha = "";
    String[] partes;
    
    try {
      File arqvDesp = new File("src/main/java/com/quatrocentosquatro/storemanagement/database/despesas.db");
      if (!arqvDesp.exists()) {
        return;
      }

      Scanner analArqvDesp = new Scanner(arqvDesp);
  
      while (analArqvDesp.hasNext()) {
        linha = analArqvDesp.nextLine();
        partes = linha.split("\\|");
        if (!linha.isEmpty()) {despesas.add(new Despesa(Integer.parseInt(partes[0]), Float.parseFloat(partes[1]), Boolean.parseBoolean(partes[2]), partes[3]));}
      }

      analArqvDesp.close();

      if (linha.isEmpty()) {
        return;
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Não foi possível listar despesas:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    if (despesas.isEmpty()) {
      System.out.println("Não há despesas");
      return;
    } else {
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

  public int getId() {return this.id;}
  public void setId(int id) {this.id = id;}

  public float getValor() {return this.valor;}
  public void setValor(float valor) {this.valor = valor;}

  public boolean getIsPago() {return this.isPago;}
  public void setIsPago(boolean isPago) {this.isPago = isPago;}
  
  public String getDescricao() {return this.descricao;}
  public void setDescricao(String descricao) {this.descricao = descricao;}

}
