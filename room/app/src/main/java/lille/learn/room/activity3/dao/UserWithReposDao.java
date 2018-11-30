package lille.learn.room.activity3.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import lille.learn.room.activity3.model.UserWithRepos;

@Dao
public interface UserWithReposDao {
    @Transaction
    @Query("SELECT * from user1")
    List<UserWithRepos> getUsersWithRepos();
}
