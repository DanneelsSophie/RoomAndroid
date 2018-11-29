package lille.learn.room.activity2.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import lille.learn.room.activity2.model.Company;
import lille.learn.room.dao.BaseDao;

@Dao
public interface CompanyDao extends BaseDao<Company> {

    @Query("DELETE FROM company")
    void deleteAll();

    @Query("SELECT * from company")
    LiveData<List<Company>> getAllCompanies();

}
