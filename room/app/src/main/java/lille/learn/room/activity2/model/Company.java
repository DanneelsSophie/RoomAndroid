package lille.learn.room.activity2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import lille.learn.room.activity2.Location;

@Entity(tableName = "Company")
public class Company {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int companyId;


    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "date_updated")
    @TypeConverters(DateConverter.class)
    private Date itemUpdatedDate;


    @Embedded
    private Location location;


    @Embedded(prefix = "hq_")
    private Location headLocation;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getItemUpdatedDate() {
        return itemUpdatedDate;
    }

    public void setItemUpdatedDate(Date itemUpdatedDate) {
        this.itemUpdatedDate = itemUpdatedDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getHeadLocation() {
        return headLocation;
    }

    public void setHeadLocation(Location headLocation) {
        this.headLocation = headLocation;
    }
}