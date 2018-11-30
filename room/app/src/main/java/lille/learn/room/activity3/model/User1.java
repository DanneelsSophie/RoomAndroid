package lille.learn.room.activity3.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User1 {
    @PrimaryKey
    public final int id;
    public final String login;
    public final String avatarUrl;

    public User1(int id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}