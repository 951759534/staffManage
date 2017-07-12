package org.staffManage.Model.Enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by K550jk on 2017/6/20.
 */
@Entity
@Table(name="staff")
@ToString
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    public String getIsHiring() {
        return isHiring;
    }

    public void setIsHiring(String isHiring) {
        this.isHiring = isHiring;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    private String staffNumber;
    private String staffName;
    private String staffPass;
    private String staffGender;
    @Temporal(TemporalType.TIMESTAMP)
    private Date Birthday;
    @Temporal(TemporalType.TIMESTAMP)
    private Date hireDay;
    private String isHiring;
    @Column(insertable=false,updatable=false)
    private int sectionId;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="sectionId",foreignKey = @ForeignKey(name = "fk_News_type"))
    @JsonBackReference    //防止递归查询
    private Section section;




    private int admin;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    private String position;
    public Staff() {
    }
}
