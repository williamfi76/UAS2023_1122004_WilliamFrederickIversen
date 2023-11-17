package UASPBO2023.Model;

public class Transactions {
    private int id, user_d, game_id;

    public Transactions() {
    }

    public Transactions(int id, int user_d, int game_id) {
        this.id = id;
        this.user_d = user_d;
        this.game_id = game_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_d() {
        return user_d;
    }

    public void setUser_d(int user_d) {
        this.user_d = user_d;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
