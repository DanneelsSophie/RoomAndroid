package lille.learn.room.activity2.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;


public class CompanyAndAllDepartments {

    @Embedded
    public Company company;
    @Relation(parentColumn = "id", entityColumn = "companyId", entity = Department.class)
    public List<Department> departments;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
