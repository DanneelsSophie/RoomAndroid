package lille.learn.room.activity3.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
@Entity
public class Repo1 {
    @PrimaryKey
    public final int id;
    public final String name;
    public final String url;
    public final int userId;

    public Repo1(int id, String name, String url,int userId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.userId = userId;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}