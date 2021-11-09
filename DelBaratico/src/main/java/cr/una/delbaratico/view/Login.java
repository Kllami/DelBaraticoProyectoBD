package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel panel;
    private JLabel userLabel, passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JCheckBox showPassword;
    private Control gestorPrincipal;
    private VentanaHome ventanaHome;


    public Login() {
        super("Login DelBaratico");
        this.gestorPrincipal = new Control();
        this.userLabel=new JLabel("Nombre de Usuario");
        this.passwordLabel=new JLabel("Password de Usuario");
        this.userTextField=new JTextField();
        this.passwordField=new JPasswordField();
        this.loginButton=new JButton("Login");
        this.resetButton=new JButton("Limpiar campos");
        this.showPassword=new JCheckBox("Mostrar Password");

        this.addComponentsToContainer();
        this.init();
        this.addListeners();
    }

    public void addComponentsToContainer() {
        this.panel = new JPanel(new GridLayout(4, 2));
        this.panel.add(userLabel);
        this.panel.add(userTextField);
        this.panel.add(passwordLabel);
        this.panel.add(passwordField);
        this.panel.add(new JLabel());
        this.panel.add(showPassword);
        this.panel.add(loginButton);
        this.panel.add(resetButton);
        this.add(panel, BorderLayout.CENTER);
    }

    public void init(){
        this.setTitle("Login DelBaratico");
        this.setVisible(true);
        this.setSize(300, 160);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }

    public void addListeners(){
        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userText;
                String pwdText;
                userText = userTextField.getText();
                pwdText = passwordField.getText();
                boolean result = gestorPrincipal.login(userTextField.getText().trim(), String.valueOf(passwordField.getPassword()).trim());
                if (result) {
                    JOptionPane.showMessageDialog(panel, "Login exitoso");
                    setVisible(false);
                    dispose();
                    ventanaHome = new VentanaHome(gestorPrincipal);
                } else {
                    JOptionPane.showMessageDialog(panel, "Passsword o nombre invalido");
                }
            }
        });

        this.resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                userTextField.setText("");
                passwordField.setText("");
            }
        });

        this.showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });
    }
}
