package com.quatrocentosquatro.storemanagement.views;

import com.quatrocentosquatro.storemanagement.controller.ProcessamentoDeVendas;
import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Venda;
import com.quatrocentosquatro.storemanagement.model.ItemVenda;
import com.quatrocentosquatro.storemanagement.controller.Financeiro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPDV extends JFrame {
    private JTextField txtIdProduto, txtQuantidade, txtDesconto;
    private JComboBox<String> cmbPagamento;
    private JButton btnAdicionar, btnFinalizar;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;

    private Venda vendaAtual;
    private ProcessamentoDeVendas processador;

    public TelaPDV(GerenciarEstoque estoqueController, Financeiro financeiro) {
        processador = new ProcessamentoDeVendas(estoqueController, financeiro);
        vendaAtual = new Venda(0); // O ID real será atribuído pelo controlador--

        setTitle("PDV - Ponto de Venda");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        txtIdProduto = new JTextField();
        txtQuantidade = new JTextField();
        txtDesconto = new JTextField();
        cmbPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "PIX"});

        painelCampos.setBorder(BorderFactory.createTitledBorder("Dados da Venda"));
        painelCampos.add(new JLabel("ID do Produto:"));
        painelCampos.add(txtIdProduto);
        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(txtQuantidade);
        painelCampos.add(new JLabel("Desconto (R$):"));
        painelCampos.add(txtDesconto);
        painelCampos.add(new JLabel("Forma de Pagamento:"));
        painelCampos.add(cmbPagamento);

        btnAdicionar = new JButton("Adicionar Item");
        btnFinalizar = new JButton("Finalizar Venda");

        modeloTabela = new DefaultTableModel(new String[]{"Produto", "Qtd", "Preço Unit.", "Desconto", "Subtotal"}, 0);
        tabela = new JTable(modeloTabela);

        lblTotal = new JLabel("Total: R$ 0.00", SwingConstants.RIGHT);

        add(painelCampos, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnFinalizar);
        painelInferior.add(painelBotoes, BorderLayout.WEST);
        painelInferior.add(lblTotal, BorderLayout.EAST);

        add(painelInferior, BorderLayout.SOUTH);

        eventos();
    }

    private void eventos() {
        btnAdicionar.addActionListener(e -> adicionarItem());

        btnFinalizar.addActionListener(e -> {
            if (vendaAtual.getItens().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Adicione ao menos um item.");
                return;
            }

            String forma = (String) cmbPagamento.getSelectedItem();
            processador.finalizarVendaGUI(vendaAtual, forma);
            JOptionPane.showMessageDialog(this, "Venda concluída!\nTotal: R$ " + String.format("%.2f", vendaAtual.getTotal()));
            dispose();
        });
    }

    private void adicionarItem() {
        try {
            int id = Integer.parseInt(txtIdProduto.getText());
            int qtd = Integer.parseInt(txtQuantidade.getText());
            double desconto = Double.parseDouble(txtDesconto.getText());

            Produto p = ((GerenciarEstoque) processador.getGerenciarEstoque()).buscarPorId(id);

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                return;
            }

            if (qtd > p.getQuantidade()) {
                JOptionPane.showMessageDialog(this, "Estoque insuficiente.");
                return;
            }

            double preco = p.getPreco();
            ItemVenda item = new ItemVenda(p, qtd, preco, desconto);
            vendaAtual.adicionarItem(item);

            modeloTabela.addRow(new Object[]{
                p.getNome(),
                qtd,
                String.format("R$ %.2f", preco),
                String.format("R$ %.2f", desconto),
                String.format("R$ %.2f", item.getSubtotal())
            });

            // Atualiza estoque na memória
            p.setQuantidade(p.getQuantidade() - qtd);

            lblTotal.setText(String.format("Total: R$ %.2f", vendaAtual.getTotal()));

            txtIdProduto.setText("");
            txtQuantidade.setText("");
            txtDesconto.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preencha os campos corretamente.");
        }
    }
}
