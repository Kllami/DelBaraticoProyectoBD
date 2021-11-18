package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;


public class LoginView extends JFrame{
    private JPanel panelPrincipal;
    private JButton iniciarSesiónButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private ServiceController servicio;
    private HomeView homeView;

    public LoginView(ServiceController servicio) {
        this.servicio = servicio;
        this.panelPrincipal.setPreferredSize(new Dimension(400,200));
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.iniciarListeners();//Siempre se llama de ultimo
        this.setTitle("DelBaratico Login");
    }

    public void iniciarListeners(){
        iniciarSesiónButton.addActionListener(e -> {
            String userName = textField1.getText().trim();
            String pwd = String.valueOf(passwordField1.getPassword()).trim();
            boolean result = servicio.iniciarSesion(userName, pwd);

            if (result) {
                JOptionPane.showMessageDialog(panelPrincipal, "Login exitoso");
                this.setVisible(false);
                this.dispose();
                homeView = new HomeView(servicio);
            } else {
                JOptionPane.showMessageDialog(panelPrincipal, "Passsword o nombre invalido");
            }
        });
    }

    public JPanel getPanel1() {
        return panelPrincipal;
    }

    public void setPanel1(JPanel panel1) {
        this.panelPrincipal = panel1;
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
