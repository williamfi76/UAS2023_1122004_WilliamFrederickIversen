package UASPBO2023.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import UASPBO2023.Controller.Controller;

public class MainMenu {
    public MainMenu() {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(350, 350);
        mainFrame.setResizable(false);
        mainFrame.setTitle("Menu Login");
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);

        JPanel loginForm = new JPanel(null);

        JLabel email = new JLabel("Email:");
        email.setBounds(10, 20, 80, 25);
        loginForm.add(email);

        JTextField emailField = new JTextField(20);
        emailField.setBounds(100, 20, 165, 25);
        loginForm.add(emailField);

        JLabel password = new JLabel("Password:");
        password.setBounds(10, 50, 80, 25);
        loginForm.add(password);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        loginForm.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(145, 95, 100, 25);
        loginForm.add(loginButton);

        loginButton.addActionListener(e -> {
            if (emailField.getText().isBlank() || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(mainFrame, "Pastikan kedua field sudah terisi", "WARNING",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                    if (Controller.checkValidity(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
                        mainFrame.dispose();
                        JOptionPane.showMessageDialog(mainFrame, "Login Sukses", "LOGIN",
                                JOptionPane.INFORMATION_MESSAGE);
                        new GameList(emailField.getText());
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Login GAGAL", "LOGIN",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        loginForm.setBounds(25, 125, 400, 200);

        mainFrame.add(loginForm);
        mainFrame.setVisible(true);
    }
}
