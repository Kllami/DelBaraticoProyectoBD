package main.java.cr.una.delbaratico.view.iniciarsesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesionView {
    private JPanel panel1;
    private JButton iniciarSesiónButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private IniciarSesionController iniciarSesionController;

    public IniciarSesionView(IniciarSesionController iniciarSesionController) {
        this.iniciarSesionController = iniciarSesionController;
        iniciarSesiónButton.addActionListener(e -> {
            iniciarSesionController.iniciarSesion(textField1.getText(), new String(passwordField1.getPassword()));
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
}
