package lille.learn.room.model;



import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * L'entity qui sera le modèle de notre table
 */
@Entity(tableName = "note_table")
public class Note implements Serializable {


    // un id généré
    @PrimaryKey(autoGenerate = true)
    private long id;

    // un string pour la note
    @NonNull
    @ColumnInfo(name = "note")
    private String note;

    /**
     * Le constructeur de l'objet Note
     * @param note une note
     */
    public Note(@NonNull String note) {
        this.note = note;
    }

    // Getters et les setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNote() {
        return note;
    }

    public void setNote(@NonNull String note) {
        this.note = note;
    }
}