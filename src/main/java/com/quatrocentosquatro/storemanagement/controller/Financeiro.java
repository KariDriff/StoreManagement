package main.java.com.quatrocentosquatro.storemanagement.controller;

import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lida com as entradas e saídas do sistema, registrando-as num log.
 * 
 * @author Kaio A. Souza
 */
public class Financeiro {
  // Para formatar em real
  NumberFormat formataReal = new DecimalFormat("R$ ###,##0.00");

  // Para puxar a data e horário atual, formatá-los e guardá-los numa String, respectivamente.
  LocalDateTime agora = LocalDateTime.now();
  DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("dd/MM, HH:mm:ss");
  String dataHoraFormatada = agora.format(formataHora);

  /**
   * Será chamado automaticamente quando houver uma venda no
   * {@code ProcessamentoDeVendas}
   *
   * @param valor (float) - O valor da entrada.
   */
  public void registrarEntrada(float valor) {
    /*
     * FileWriter escreverEntrada = new FileWriter("null");
     * escreverEntrada.append("\n[" + dataHoraFormatada + "] Entrada de " + formataReal.format(valor));
     * escreverEntrada.close();
     *
     * TODO Especificar onde ficará o log de Financeiro.
     */

    registrarOperacoes("\n[" + dataHoraFormatada + "] Entrada de " + formataReal.format(valor));
  }

  /**
   * Diferentemente do {@code registrarEntrada()}, este será chamado manualmente
   * pelo usuário.
   */
  public void registrarEntradaUser() {
    // TODO Exibir na interface o processo de registrar manualmente a entrada.

    // registrarOperacoes("\n[" + dataHoraFormatada + "] Compra no valor de " + formataReal.format(/* Valor */));
  }

  /**
   * Será chamado automaticamente quando houver uma compra no
   * {@code comprarProdutos()}.
   *
   * @param valor (float) - O valor da entrada.
   */
  public void registrarSaida(float valor) {
    /*
     * FileWriter escreverSaida = new FileWriter("null");
     * escreverSaida.append("\n[" + dataHoraFormatada + "] Saída de " + formataReal.format(valor));
     * escreverSaida.close();
     *
     * TODO Especificar onde ficará o log de Financeiro.
     */

    registrarOperacoes("\n[" + dataHoraFormatada + "] Saída de " + formataReal.format(valor));
  }

  /**
   * Diferentemente do {@code registrarSaida()}, este será chamado manualmente
   * pelo usuário.
   */
  public void registrarSaidaUser() {
    // TODO Exibir na interface o processo de registrar manualmente a saída.

    // registrarOperacoes("\n[" + dataHoraFormatada + "] Compra no valor de " + formataReal.format(/* Valor */));
  }

  /** 
   * Realiza a compra de produtos de baixo estoque.
   */
  public void comprarProdutos() {
    /*
     * TODO Exbir interface para fazer a compra de produtos com baixo estoque.
     * Deve ser chamado o método verificarQtdProdutos() para listar os produtos com
     * baixo estoque.
     */

    // registrarOperacoes("\n [" + dataHoraFormatada + "] Produtos comprados no valor de " + formataReal.format(/* Valor */));
  }

  /**
   * Realiza o pagamento de uma despesa, utilizando deu ID.
   * 
   * @param id (int) - O ID da despesa que será paga.
   */
  public void pagarDespesa(int id) {
    /*
    if (Despesa existe) {
      Alterar isPago para true;

      registrarOperacoes("\n[" + dataHoraFormatada + "] Despesa de ID " + id + " paga.");
    } else if (Despesa não existe) {
      JOptionPane.showMessageDialog(null, "Não foi possível pagar despesa: Despesa não existe", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Não foi possível pagar despesa: Erro desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
    }    
    */
  }


  /**
   * Registra as ações feitas pela classe.
   * 
   * @param acao (String) - A ação a ser registrada.
   */
  private void registrarOperacoes(String acao) {
    try {
      FileWriter escreverAcao = new FileWriter("null"); // TODO Especificar onde ficará o log geral.
      escreverAcao.append(acao);
      escreverAcao.close();
    } catch (Exception IOException) {
      // Exibe erro quando, bem, houver um erro.
      JOptionPane.showMessageDialog(null, "Não foi possível registrar ações: Erro desconhecido.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }
}
