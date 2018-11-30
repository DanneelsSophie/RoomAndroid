package lille.learn.room.activity3.dao;

import android.arch.persistence.room.Dao;

import lille.learn.room.activity3.model.User;
import lille.learn.room.dao.BaseDao;

@Dao
public interface UserDao extends BaseDao<User> {
}
