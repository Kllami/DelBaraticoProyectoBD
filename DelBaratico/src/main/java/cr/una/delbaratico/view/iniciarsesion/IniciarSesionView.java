package main.java.cr.una.delbaratico.view.iniciarsesion;

import javax.swing.*;
import java.awt.*;

public class IniciarSesionView extends JFrame{
    private JPanel panel1;
    private JButton iniciarSesiónButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private IniciarSesionController iniciarSesionController;

    public IniciarSesionView(IniciarSesionController iniciarSesionController) {
        this.iniciarSesionController = iniciarSesionController;
        iniciarListeners();
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void iniciarListeners(){
        iniciarSesiónButton.addActionListener(e -> {
            String userName = textField1.getText().trim();
            String pwd = String.valueOf(passwordField1.getPassword()).trim();
            boolean result = iniciarSesionController.iniciarSesion(userName, pwd);

            if (result) {
                JOptionPane.showMessageDialog(panel1, "Login exitoso");
                Window win = SwingUtilities.getWindowAncestor(panel1);
                win.setVisible(false);
                win.dispose();

                //ventanaHome = new VentanaHome(gestorPrincipal);
            } else {
                JOptionPane.showMessageDialog(panel1, "Passsword o nombre invalido");
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getIniciarSesiónButton() {
        return iniciarSesiónButton;
    }

    public void setIniciarSesiónButton(JButton iniciarSesiónButton) {
        this.iniciarSesiónButton = iniciarSesiónButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
