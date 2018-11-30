package lille.learn.room.activity2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Company.class,
        parentColumns = "id",
        childColumns = "company_id",
        onDelete = ForeignKey.CASCADE),
        indices = {
                @Index(value="company_id")})
public class Employee {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int employeeId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "company_id")
    private int companyId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}