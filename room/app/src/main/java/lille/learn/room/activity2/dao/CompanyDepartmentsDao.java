package lille.learn.room.activity2.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import lille.learn.room.activity2.model.CompanyAndAllDepartments;

@Dao
public interface CompanyDepartmentsDao {
    @Transaction
    @Query("SELECT * FROM Company WHERE id = :companyId")
    CompanyAndAllDepartments loadCompanyAllDepartments(long companyId);
}