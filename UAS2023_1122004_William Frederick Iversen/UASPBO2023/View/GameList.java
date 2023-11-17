package UASPBO2023.View;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import UASPBO2023.Controller.Controller;
import UASPBO2023.Model.Game;
import UASPBO2023.Model.User;

public class GameList {
    public GameList(String email) {

        User currUser = Controller.getUserBaseEmail(email);

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 850);
        mainFrame.setResizable(false);
        mainFrame.setTitle("Menu Login");
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);

        JButton transactionsButton = new JButton("Histori Transaksi");
        transactionsButton.setBounds(200, 10, 200, 25);
        mainFrame.add(transactionsButton);

        int initial_height = 70;
        Font headerFont = new Font("Arial", Font.BOLD, 14);

        JLabel labelNama = new JLabel("Nama Game");
        labelNama.setFont(headerFont);
        labelNama.setBounds(10, 25, 50, 25);
        mainFrame.add(labelNama);

        JLabel labelGenre = new JLabel("Genre Game");
        labelGenre.setFont(headerFont);
        labelGenre.setBounds(65, 25, 50, 25);
        mainFrame.add(labelGenre);

        JLabel labelHarga = new JLabel("Harga Game");
        labelHarga.setFont(headerFont);
        labelHarga.setBounds(120, 25, 50, 25);
        mainFrame.add(labelHarga);

        ArrayList<Game> games = Controller.getAllGames();
        for (int i = 0; i < games.size(); i++) {
            JLabel namaGame = new JLabel(games.get(i).getName());
            namaGame.setBounds(10, initial_height, 50, 25);
            mainFrame.add(namaGame);

            JLabel genreGame = new JLabel(games.get(i).getGenre());
            genreGame.setBounds(65, initial_height, 50, 25);
            mainFrame.add(genreGame);

            JLabel hargaGame = new JLabel("" + games.get(i).getPrice());
            hargaGame.setBounds(120, initial_height, 50, 25);
            mainFrame.add(hargaGame);

            JButton buyButton = new JButton("Buy");
            buyButton.setBounds(190, initial_height, 100, 25);
            mainFrame.add(buyButton);

            final int idGame = games.get(i).getId();

            buyButton.addActionListener(e -> {
                try {
                    Controller.insertNewTransaction(currUser.getId(), idGame);
                    mainFrame.dispose();
                    new MainMenu();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(buyButton, "GAGAL", "GAGAL", JOptionPane.ERROR_MESSAGE);
                }
            });

            initial_height += 30;
        }

        mainFrame.setVisible(true);
    }
}
