package com.quatrocentosquatro.storemanagement.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.quatrocentosquatro.storemanagement.controller.Financeiro;
import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.controller.ProcessamentoDeVendas;
import com.quatrocentosquatro.storemanagement.model.ItemVenda;
import com.quatrocentosquatro.storemanagement.model.Produto;
import com.quatrocentosquatro.storemanagement.model.Venda;

/**
 * @author Everton Ferreira
 */
public class TelaPDV extends JFrame {
    private JTextField txtIdProduto, txtQuantidade, txtDesconto;
    private JComboBox<String> cmbPagamento;
    private JButton btnAdicionar, btnFinalizar, buttonHome;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JLabel lblTotal;

    private Venda vendaAtual;
    private ProcessamentoDeVendas processador;

    public TelaPDV(GerenciarEstoque estoqueController, Financeiro financeiro) {
        processador = new ProcessamentoDeVendas(estoqueController, financeiro);
        vendaAtual = new Venda(0); // O ID real será atribuído pelo controlador--

        setTitle("PDV - Ponto de Venda ---- Em desenvolvimento:(");
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
        buttonHome = new JButton("Home");

        modeloTabela = new DefaultTableModel(new String[]{"Produto", "Qtd", "Preço Unit.", "Desconto", "Subtotal"}, 0);
        tabela = new JTable(modeloTabela);

        lblTotal = new JLabel("Total: R$ 0.00", SwingConstants.RIGHT);

        add(painelCampos, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        JPanel painelBotoesEsquerda = new JPanel();
        JPanel painelBotoesDireita = new JPanel();

        painelBotoesEsquerda.add(btnAdicionar);
        painelBotoesEsquerda.add(btnFinalizar);

        painelBotoesDireita.add(buttonHome);
        painelBotoesDireita.add(lblTotal);

        painelInferior.add(painelBotoesEsquerda, BorderLayout.WEST);
        painelInferior.add(painelBotoesDireita, BorderLayout.EAST);

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

        buttonHome.addActionListener(e -> {
            new TelaHome().setVisible(true);
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
