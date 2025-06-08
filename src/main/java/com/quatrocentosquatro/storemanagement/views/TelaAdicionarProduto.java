package com.quatrocentosquatro.storemanagement.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.quatrocentosquatro.storemanagement.controller.GerenciarEstoque;
import com.quatrocentosquatro.storemanagement.model.Produto;

/**
 * @author Everton
 */
public class TelaAdicionarProduto extends JFrame {

    private JTextField campoProduto;
    private JTextField campoMarca;
    private JTextField campoPorId;
    private JTextField campoQuantidade;
    private JTextField campoLote;
    private JTextField campoCodigoBarras;
    private JTextField campoDataValidade;
    private JTextField campoPesoGramas;
    private JTextField campoPreco;
    private JButton buttonCadastrar;
    private JButton buttonVoltar;
    private JButton buttonHome;

    private GerenciarEstoque gerenciador = new GerenciarEstoque(); // Conexão com lógica

    public TelaAdicionarProduto() {
        setTitle("Adicionar Produto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo Produto
        campoProduto = new JTextField("Produto");
        campoProduto.setBounds(30, 30, 160, 30);
        campoProduto.addMouseListener(getPlaceholderClearListener(campoProduto, "Produto"));
        add(campoProduto);

        // Campo Marca
        campoMarca = new JTextField("Marca");
        campoMarca.setBounds(210, 30, 160, 30);
        campoMarca.addMouseListener(getPlaceholderClearListener(campoMarca, "Marca"));
        add(campoMarca);

        // Campo ID (informativo)
        campoPorId = new JTextField("ID");
        campoPorId.setBounds(390, 30, 160, 30);
        campoPorId.setEditable(false);
        add(campoPorId);

        // Campo Quantidade
        campoQuantidade = new JTextField("Quantidade");
        campoQuantidade.setBounds(30, 80, 160, 30);
        campoQuantidade.addMouseListener(getPlaceholderClearListener(campoQuantidade, "Quantidade"));
        add(campoQuantidade);

        // Campo Lote
        campoLote = new JTextField("Lote");
        campoLote.setBounds(210, 80, 160, 30);
        campoLote.addMouseListener(getPlaceholderClearListener(campoLote, "Lote"));
        add(campoLote);

        // Campo Código de Barras
        campoCodigoBarras = new JTextField("Código de Barras");
        campoCodigoBarras.setBounds(390, 80, 160, 30);
        campoCodigoBarras.addMouseListener(getPlaceholderClearListener(campoCodigoBarras, "Código de Barras"));
        add(campoCodigoBarras);

        // Campo Data de Validade
        campoDataValidade = new JTextField("Data Validade");
        campoDataValidade.setBounds(30, 130, 160, 30);
        campoDataValidade.addMouseListener(getPlaceholderClearListener(campoDataValidade, "Data Validade"));
        add(campoDataValidade);

        // Campo Peso
        campoPesoGramas = new JTextField("Peso (g)");
        campoPesoGramas.setBounds(210, 130, 160, 30);
        campoPesoGramas.addMouseListener(getPlaceholderClearListener(campoPesoGramas, "Peso (g)"));
        add(campoPesoGramas);

        // Campo Preço
        campoPreco = new JTextField("Preço");
        campoPreco.setBounds(390, 130, 160, 30);
        campoPreco.addMouseListener(getPlaceholderClearListener(campoPreco, "Preço"));
        add(campoPreco);

        // Botão Cadastrar
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.setBounds(390, 300, 120, 30);
        add(buttonCadastrar);

        buttonCadastrar.addActionListener(e -> {
            Produto produto = new Produto();
            try {
                produto.setNome(campoProduto.getText());
                produto.setMarca(campoMarca.getText());
                produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
                produto.setLote(campoLote.getText());
                produto.setCodigoBarras(campoCodigoBarras.getText());
                produto.setDataValidade(campoDataValidade.getText());
                produto.setPesoGramas(Integer.parseInt(campoPesoGramas.getText()));
                produto.setPreco(Float.parseFloat(campoPreco.getText()));

                gerenciador.adicionarProduto(produto);
                campoPorId.setText("ID: " + produto.getId());

                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos");
            }
        });

        // Botão Voltar
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.setBounds(30, 300, 100, 30);
        add(buttonVoltar);

        // Botão Home
        buttonHome = new JButton("Home");
        buttonHome.setBounds(150, 300, 100, 30);
        add(buttonHome);
    }

    // Limpa texto padrão ao clicar
    private MouseAdapter getPlaceholderClearListener(JTextField field, String placeholder) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdicionarProduto().setVisible(true));
    }
}
