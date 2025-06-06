package views;

import javax.swing.JFrame;

public class TelaLogin {
    public static void main(String[] args) {
        //titulo da tela
        JFrame frame = new JFrame("Login");
        // aqui é a configuracao
        frame.setSize(300, 200);

        //função para fecha 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
    }
}
