package lille.learn.room.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import lille.learn.room.model.Note;


@Dao
public interface NoteDao extends BaseDao<Note>{

    /**
     * une requête pour avoir les notes en étant en ordre alphabétique
     * @return une liste (cela peut être une liste List au lieu d'un LiveData mais ici
     * on a besoin pour le visuel )
     */
    @Query("SELECT * from note_table ORDER BY note ASC")
    LiveData<List<Note>> getAlphabetizedNotes();


    /**
     * supprimer
     */
    @Query("DELETE FROM note_table")
    void deleteAll();


}
