package lille.learn.room.activity3.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

import lille.learn.room.activity3.model.Repo;
import lille.learn.room.activity3.model.User;
import lille.learn.room.activity3.model.UserRepoJoin;

@Dao
public interface UserRepoJoinDao {
    @Insert
    void insert(UserRepoJoin userRepoJoin);


    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM user INNER JOIN user_repo_join ON  user.id=user_repo_join.userId WHERE  user_repo_join.repoId=:repoId")
    List<User> getUsersForRepository(final int repoId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query( "SELECT * FROM repo INNER JOIN user_repo_join ON repo.id=user_repo_join.repoId WHERE  user_repo_join.userId=:userId")
    List<Repo> getRepositoriesForUsers(final int userId);
}
