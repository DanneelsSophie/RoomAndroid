package lille.learn.room.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

public interface BaseDao<T> {
    // insert peut renvoyer un long, qui est le nombre d'élement qui a été ajouté
    // car on peut insérer plusieurs objets en même temps comme T... obj
    // si c'est une un tableau ou une collection il renvera une liste long[] ou List<Long>
    @Insert
    void insert(T obj);

    // il peut renvoyer un int, ce qui est intéréssant si on modifie plusieurs objet en même temps
    // int update( T... obj)
    @Update
    void update(T obj);

    // pareil pour delete
    @Delete
    void delete(T obj);

}
