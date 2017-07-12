package org.staffManage.Model.Enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by K550jk on 2017/6/20.
 */
@Entity
@Table(name="section")
@ToString
public class Section implements Serializable{
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
            this.manageId = manageId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sectionId;
    @Column(unique = true)
    private String sectionName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date buildTime;
    @Column(insertable=false,updatable = false)
    private Integer manageId;
    public int  getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    private int sectionNumber;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "manageId")
    @JsonBackReference    //防止递归查询
    private Staff staff;



    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH},mappedBy = "section")
    @JsonBackReference
    private List<Staff> staffs = new ArrayList();
}
