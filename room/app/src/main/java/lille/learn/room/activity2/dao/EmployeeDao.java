package lille.learn.room.activity2.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import lille.learn.room.activity2.model.Employee;
import lille.learn.room.dao.BaseDao;

@Dao
public interface EmployeeDao extends BaseDao<Employee> {

    @Query("SELECT * from employee")
    LiveData<List<Employee>> getAllEmployee();

}
