package com.quatrocentosquatro.storemanagement.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.quatrocentosquatro.storemanagement.model.Despesa;

/**
 * Lida com as entradas e saídas do sistema, registrando-as num log.
 * 
 * @author Kaio A. Souza
 * @author João M. Chervinski
 */
public class Financeiro {

  private final NumberFormat formataReal = new DecimalFormat("R$ ###,##0.00");
  private final DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  private final String caminhoLog = "src/main/java/com/quatrocentosquatro/storemanagement/logs/financeiro.log";

  /**
   * Usado para puxar a data e horário atual do sistema.
   * 
   * @return A data e hora atual no formato dd/MM/yyyy, hh:mm:ss
   */
  private String agora() {return LocalDateTime.now().format(formataHora);}

  /**
   * Automaticamente registra uma entrada quando houver uma compra no
   * {@code ProcessamentoDeVendas}.
   * 
   * @param valor     (float)  - O valor da entrada.
   * @param descricao (String) - A descrição dela.
   */
  public void registrarEntrada(float valor, String descricao) {
    String log = "[" + agora() + "] Entrada automática: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  /**
   * Registra entradas feitas manualmente pelo usuário.
   * 
   * @param valor     (float)  - O valor da entrada.
   * @param descricao (String) - A descrição dela.
   */
  public void registrarEntradaUser(float valor, String descricao) {
    String log = "[" + agora() + "] Entrada manual: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  /**
   * Automaticamente registra uma saída quando houver uma despesa.
   * 
   * @param valor     (float)  - O valor da saída.
   * @param descricao (String) - A descrição dela.
   */
  public void registrarSaida(float valor, String descricao) {
    String log = "[" + agora() + "] Saída automática: " + formataReal.format(valor) + ". Descrição: " + descricao;
    registrarOperacoes(log);
  }

  /**
   * Registra saídas feitas manualmente pelo usuário, salvando-as com um id.
   * 
   * @param valor     (float)  - O valor da saída.
   * @param descricao (String) - A descrição dela.
   */
  public void registrarSaidaUser(float valor, boolean isPago, String descricao) {
    Despesa d = new Despesa();

    // Recebe o retorno do método da despesa
    boolean isAddDesp = d.adicionarDespesa(valor, isPago, descricao);
  
    // Utiliza a variável pra saber se a despesa foi adicionada com sucesso.
    if (isAddDesp == true) {
      String log = "[" + agora() + "] Saída manual registrada no valor de " + formataReal.format(valor) + " com a descrição: " + descricao;
      registrarOperacoes(log);
    } else {
      String log = "[" + agora() + "] Saída manual não registrada no valor de " + formataReal.format(valor) + " com a descrição: " + descricao + "";
      registrarOperacoes(log);
    }
  }

  /**
   * Registra uma compra de produtos.
   * 
   * @param totalCompra (float) - O valor total da compra.
   */
  public void comprarProdutos(float totalCompra) {
    String log = "[" + agora() + "] Compra de produtos realizada. Total: " + formataReal.format(totalCompra);
    registrarOperacoes(log);
  }

  /**
   * Paga uma despesa, utilizando seu ID para tal.
   * 
   * @param id (int) - O ID da despesa.
   */
  public void pagarDespesa(int id) {
    Despesa d = new Despesa();

    if (d.buscarPorId(id).getId() == id) {
      String log = "[" + agora() + "] Despesa de ID " + id + " paga.";
      registrarOperacoes(log);
      JOptionPane.showMessageDialog(null, "Despesa paga com sucesso!", "Despesa", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possível pagar despesa: Despesa não existe", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Gera um relatório da classe e o salva na área de trabalho.
   */
  public void gerarRelatorio() {
    try {
      File arqv = new File(caminhoLog);

      // Erro caso o log não exista.
      if (!arqv.exists()) {
        JOptionPane.showMessageDialog(null, "Não foi possível gerar relatório: log não existe", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      Scanner analArqv = new Scanner(arqv);

      // Salva o relatório na área de trabalho do usuário.
      String caminhoAreaTrabalho = System.getProperty("user.home") + File.separator + "Desktop";
      File relatorio = new File(caminhoAreaTrabalho, "relatorio_financeiro.log");
      
      FileWriter escrRela = new FileWriter(relatorio, false); // Para escrever no arquivo.

      // Lê linha do log, registra na variável e insere no relatório.
      while (analArqv.hasNextLine()) {
        String linha = analArqv.nextLine();
        escrRela.write(linha + "\n");
      }

      escrRela.close();
      analArqv.close();

      JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!\nCheque sua área de trabalho.", "Relatório", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Não foi possível gerar relatório:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Registra as operações feitas na classe em seu log
   * 
   * @param acao (String) - A operação feita.
   */
  private void registrarOperacoes(String acao) {
    try {
      FileWriter escreverAcao = new FileWriter(caminhoLog, true); // Modo append = true
      escreverAcao.append(acao).append("\n");
      escreverAcao.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Não foi possível registrar ação no financeiro:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }
}