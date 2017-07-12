package org.staffManage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.staffManage.Model.Enitity.Staff;
import org.staffManage.Model.Service.SectionService;
import org.staffManage.Model.Service.StaffService;

import java.text.SimpleDateFormat;

/**
 * Created by K550jk on 2017/6/21.
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    @Qualifier(value = "sectionService")
    private SectionService sectionService;
    @Autowired
    @Qualifier(value = "staffService")
    private StaffService staffService;
    @RequestMapping("/saveStaff")
    public String saveStaff(){
        Staff staff = new Staff();
        try {
            staff.setStaffName("星儿");
            staff.setStaffPass("2222");
            staff.setStaffGender("女");
            staff.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse("1980-01-01 "));
            staff.setHireDay(new SimpleDateFormat("yyyy-mm-dd").parse("2010-01-01"));
            staff.setAdmin(0);
            staff.setIsHiring("是");
            staff.setSection(sectionService.getSectionById(1));
            staff.setStaffNumber(staffService.getGetNewNum(1));
            staffService.saveStaff(staff);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }
    @RequestMapping("/index")
    public String index(){
        return "staff_index";
    }
    @RequestMapping("/adminIndex")
    public String adminIndex(){
        return  "admin_index";
    }
}
