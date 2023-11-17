package UASPBO2023.Controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import UAS2022.Model.CategoryUser;
import UASPBO2023.Model.Game;
import UASPBO2023.Model.Transactions;
import UASPBO2023.Model.User;

/**
 *
 * @author hanschristian
 */
public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("pass"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // SELECT ALL from table games
    public static ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM games";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Game game = new Game();
                game.setId(Integer.parseInt(rs.getString("id")));
                game.setPrice(Integer.parseInt(rs.getString("price")));
                game.setName(rs.getString("name"));
                game.setGenre(rs.getString("genre"));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (games);
    }

    // SELECT ALL from table users
    public static ArrayList<Transactions> getAllTf() {
        ArrayList<Transactions> tfs = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM transactions";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transactions tf = new Transactions();
                tf.setId(Integer.parseInt(rs.getString("id")));
                tf.setUser_d(Integer.parseInt(rs.getString("user_id")));
                tf.setGame_id(Integer.parseInt(rs.getString("game_id")));
                tfs.add(tf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (tfs);
    }

    public static int generateUserID() {
        ArrayList<Transactions> tf = Controller.getAllTf();
        int i = 1;
        boolean availableID = false;
        while (!availableID) {
            availableID = true;
            for (Transactions user : tf) {
                if (i == user.getId()) {
                    availableID = false;
                    break;
                }
            }
            if (!availableID) {
                i++;
            }
        }
        return i;
    }

    public static int getIdCategory(String categoryName) {
        ArrayList<CategoryUser> categories = Controller.getAllUserCategories();
        for (CategoryUser category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category.getId();
            }
        }

        return -1;
    }

    // SELECT ALL from table userCategory
    public static ArrayList<CategoryUser> getAllUserCategories() {
        ArrayList<CategoryUser> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM category_user";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                CategoryUser user = new CategoryUser();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setName(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // // SELECT WHERE
    // public static User getUser(String name, String address) {
    // conn.connect();
    // String query = "SELECT * FROM users WHERE Name='" + name + "'&&Address='" +
    // address + "'";
    // User user = new User();
    // try {
    // Statement stmt = conn.con.createStatement();
    // ResultSet rs = stmt.executeQuery(query);
    // while (rs.next()) {
    // user.setId(rs.getInt("ID"));
    // user.setName(rs.getString("Name"));
    // user.setAddress(rs.getString("Address"));
    // user.setPhone(rs.getString("Phone"));
    // user.setAge(rs.getInt("Age"));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return (user);
    // }

    // // SELECT WHERE ID
    // public static User getUserByID(String inputNIK) {
    //     conn.connect();
    //     String query = "SELECT * FROM ktp WHERE nik='" + inputNIK + "'";
    //     User user = new User();
    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);
    //         while (rs.next()) {
    //             user.setNik(rs.getString("nik"));
    //             user.setNama(rs.getString("nama"));
    //             user.setTempatLahir(rs.getString("tempatLahir"));
    //             user.setTanggalLahir(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("tanggalLahir")));
    //             user.setGender(rs.getString("gender"));
    //             user.setGolDar(rs.getString("goldar"));
    //             user.setAlamat(rs.getString("alamat"));
    //             user.setRtRW(rs.getString("RTRW"));
    //             user.setKelurahan(rs.getString("kelurahan"));
    //             user.setKecamatan(rs.getString("kecamatan"));
    //             user.setAgama(rs.getString("agama"));
    //             user.setStatusKawin(rs.getString("statusKawin"));
    //             user.setPekerjaan(rs.getString("pekerjaan"));
    //             user.setKewarganegaraan(rs.getString("kewarganegaraan"));
    //             user.setFoto(rs.getString("foto"));
    //             user.setTtd(rs.getString("ttd"));
    //             user.setTanggalBerlaku(rs.getString("tanggalBerlaku"));
    //             user.setKotaPembuatan(rs.getString("kotaPembuatan"));
    //             user.setTanggalPembuatan(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("tanggalPembuatan")));
    //         }
    //     } catch (SQLException | ParseException e) {
    //         e.printStackTrace();
    //     }
    //     return (user);
    // }

    // INSERT

    public static String dateFormatter(Date inputDate) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        return dateFormater.format(inputDate);
    }

    public static User getUserBaseEmail(String email) {
        ArrayList<User> users = Controller.getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }

        return null;
    }

    public static boolean checkValidity(String email, String pass) {
        ArrayList<User> users = Controller.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (pass.equals(user.getPassword())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertNewTransaction(int idUser, int idGame) {
        conn.connect();
        String query = "INSERT INTO transactions VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, generateUserID());
            stmt.setInt(2, idUser);
            stmt.setInt(3, idGame);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // // UPDATE
    // public static boolean updateUser(User user) {
    //     conn.connect();
    //     String query = "UPDATE `ktp` SET" +
    //             "`nama`= '" + user.getNama() + "'," +
    //             "`tempatLahir`= '" + user.getTempatLahir() + "'," +
    //             "`tanggalLahir`= '" + dateFormatter(user.getTanggalLahir()) + "'," +
    //             "`gender`= '" + user.getGender() + "'," +
    //             "`goldar`= '" + user.getGolDar() + "'," +
    //             "`alamat`= '" + user.getAlamat() + "'," +
    //             "`RTRW`= '" + user.getRtRW() + "'," +
    //             "`kelurahan`= '" + user.getKelurahan() + "'," +
    //             "`kecamatan`= '" + user.getKecamatan() + "'," +
    //             "`agama`= '" + user.getAgama() + "'," +
    //             "`statusKawin`= '" + user.getStatusKawin() + "'," +
    //             "`pekerjaan`= '" + user.getPekerjaan() + "'," +
    //             "`kewarganegaraan`= '" + user.getKewarganegaraan() + "'," +
    //             "`foto`= '" + user.getFoto() + "'," +
    //             "`ttd`= '" + user.getTtd() + "'," +
    //             "`tanggalBerlaku`= '" + user.getTanggalBerlaku() + "'," +
    //             "`kotaPembuatan`= '" + user.getKotaPembuatan() + "'," +
    //             "`tanggalPembuatan`= '" + dateFormatter(user.getTanggalPembuatan()) + "' " +
    //             "WHERE nik = '" + user.getNik() + "';";

    //     try {
    //         Statement stmt = conn.con.createStatement();
    //         stmt.executeUpdate(query);
    //         return (true);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return (false);
    //     }
    // }

    // DELETE
    public static boolean deleteUser(String nik) {
        conn.connect();

        String query = "DELETE FROM ktp WHERE nik='" + nik + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static int cariIndex(String agama, String[] arrAgama) {
        for (int i = 0; i < arrAgama.length; i++) {
            if (arrAgama[i].equals(agama)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean inArray(String[] pekerjaan, String text) {
        for (int i = 0; i < pekerjaan.length; i++) {
            if (text.equalsIgnoreCase(pekerjaan[i])) {
                return true;
            }
        }
        return false;
    }
}
