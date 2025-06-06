package com.quatrocentosquatro.storemanagement.controller;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lida com as entradas e saídas do sistema, registrando-as num log.
 * Refatorado para armazenar operações em arquivo.
 * 
 * @author Kaio
 */
public class Financeiro {

  private final NumberFormat formataReal = new DecimalFormat("R$ ###,##0.00");
  private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  private final String caminhoLog = "logs/financeiro.log"; // Local real para salvar o log

  private String agora() {
    return LocalDateTime.now().format(formataHora);
  }

  public void registrarEntrada(float valor, String descricao) {
    String log = "[" + agora() + "] Entrada automática: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  public void registrarEntradaUser(float valor, String descricao) {
    String log = "[" + agora() + "] Entrada manual: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  public void registrarSaida(float valor, String descricao) {
    String log = "[" + agora() + "] Saída automática: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  public void registrarSaidaUser(float valor, String descricao) {
    String log = "[" + agora() + "] Saída manual: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  public void comprarProdutos(float totalCompra) {
    String log = "[" + agora() + "] Compra de produtos realizada. Total: " + formataReal.format(totalCompra);
    registrarOperacoes(log);
  }

  public void pagarDespesa(int id, boolean despesaExiste) {
    if (despesaExiste) {
      String log = "[" + agora() + "] Despesa de ID " + id + " paga.";
      registrarOperacoes(log);
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possível pagar despesa: Despesa não existe", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void registrarOperacoes(String acao) {
    try {
      FileWriter escreverAcao = new FileWriter(caminhoLog, true); // Modo append = true
      escreverAcao.append(acao).append("\n");
      escreverAcao.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Não foi possível registrar ação no financeiro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }
}
