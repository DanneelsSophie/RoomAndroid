package lille.learn.room;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lille.learn.room.activity2.Location;
import lille.learn.room.activity2.dao.CompanyDao;
//import lille.learn.room.activity2.dao.CompanyDepartmentsDao;
import lille.learn.room.activity2.dao.DepartmentDao;
import lille.learn.room.activity2.dao.EmployeeDao;
import lille.learn.room.activity2.model.Company;
//import lille.learn.room.activity2.model.CompanyAndAllDepartments;
import lille.learn.room.activity2.model.Department;
import lille.learn.room.activity2.model.Employee;
import lille.learn.room.dao.NoteDao;
import lille.learn.room.database.NoteRoomDatabase;
import lille.learn.room.model.Note;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ActivityTest2 {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CompanyDao mCompany;
    private NoteDao mNoteDao;
    private NoteRoomDatabase mDb;
    private EmployeeDao mEmployeeDao;
    private DepartmentDao departmentDao;
    //private CompanyDepartmentsDao compDepartDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, NoteRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        mNoteDao = mDb.noteDao();
        mCompany = mDb.companyDao();
        //mEmployeeDao = mDb.employeeDao();
        //departmentDao = mDb.departmentDao();
        //compDepartDao = mDb.companyAndAllDepartmentsDao();

    }

    @After
    public void closeDb() {
        mDb.close();
    }


    @Test
    public void insertAndGetCompany() throws Exception {
        Company c = new Company();
        Date d = new Date("08/03/1992");
        c.setItemUpdatedDate(d);
        Location l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Company Tarby");
        mCompany.insert(c);
        d = new Date("12/08/1961");
        c.setItemUpdatedDate(d);
        l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Carrefour");
        mCompany.insert(c);
        List<Company> companies = LiveDataTestUtil.getValue(mCompany.getAllCompanies());
        assertEquals("Company Tarby",companies.get(0).getName());
        assertEquals("Carrefour",companies.get(1).getName());
        assertEquals(new Double(56),companies.get(1).getHeadLocation().getLatitude());
        assertEquals(new Double(34),companies.get(1).getHeadLocation().getLongitude());
        assertEquals(d,companies.get(1).getItemUpdatedDate());
    }

    /**@Test
    public void insertAndGetEmployee() throws Exception {
        Company c = new Company();
        Date d = new Date("08/03/1992");
        c.setItemUpdatedDate(d);
        Location l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Company Tarby");
        mCompany.insert(c);
        d = new Date("12/08/1961");
        c.setItemUpdatedDate(d);
        l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Carrefour");
        mCompany.insert(c);
        List<Company> companies = LiveDataTestUtil.getValue(mCompany.getAllCompanies());
        Employee e = new Employee();
        e.setCompanyId(companies.get(0).getCompanyId());
        e.setEmployeeId(1);
        e.setName("Patrick");
        mEmployeeDao.insert(e);
        List<Employee> employees = LiveDataTestUtil.getValue(mEmployeeDao.getAllEmployee());
        assertEquals("Patrick",employees.get(0).getName());

    }




    @Test
    public void DeleteCascade() throws Exception {
        Company c = new Company();
        Date d = new Date("08/03/1992");
        c.setItemUpdatedDate(d);
        Location l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Company Tarby");
        mCompany.insert(c);
        d = new Date("12/08/1961");
        c.setItemUpdatedDate(d);
        l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Carrefour");
        mCompany.insert(c);
        List<Company> companies = LiveDataTestUtil.getValue(mCompany.getAllCompanies());
        Employee e = new Employee();
        e.setCompanyId(companies.get(0).getCompanyId());
        e.setEmployeeId(1);
        e.setName("Patrick");
        mEmployeeDao.insert(e);
        assertEquals("Patrick",e.getName());
        mCompany.delete(companies.get(0));
        List<Employee> employees = LiveDataTestUtil.getValue(mEmployeeDao.getAllEmployee());
        assertEquals(0,employees.size());
    }



    @Test
    public void DepartementCompanyWithRelation() throws Exception {
        Company c = new Company();
        Date d = new Date("08/03/1992");
        c.setItemUpdatedDate(d);
        Location l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Company Tarby");
        mCompany.insert(c);
        d = new Date("12/08/1961");
        c.setItemUpdatedDate(d);
        l = new Location();
        l.setLatitude(56);
        l.setLongitude(34);
        c.setHeadLocation(l);
        c.setName("Carrefour");
        mCompany.insert(c);
        List<Company> companies = LiveDataTestUtil.getValue(mCompany.getAllCompanies());
        Employee e = new Employee();
        e.setCompanyId(companies.get(0).getCompanyId());
        e.setEmployeeId(1);
        e.setName("Patrick");
        mEmployeeDao.insert(e);
        Department depart = new Department();
        depart.setCompanyId(companies.get(0).getCompanyId());
        depart.setName("Departement1");
        depart.setId(1);
        departmentDao.insert(depart);

        depart.setCompanyId(companies.get(0).getCompanyId());
        depart.setName("Departement2");
        depart.setId(2);
        departmentDao.insert(depart);


        assertEquals("Company Tarby",compDepartDao.loadCompanyAllDepartments(1).getCompany().getName());

        assertEquals("Departement1",compDepartDao.loadCompanyAllDepartments(1).getDepartments().get(0).getName());
        assertEquals("Departement2",compDepartDao.loadCompanyAllDepartments(1).getDepartments().get(1).getName());

    }

    **/


}
