package org.staffManage.Model.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.staffManage.Model.Enitity.Section;
import org.staffManage.Model.Enitity.Staff;
import org.staffManage.Model.Repository.SectionRepository;
import org.staffManage.Model.Repository.StaffRepository;

import java.util.Map;

/**
 * Created by K550jk on 2017/6/20.
 */
@Service
public class StaffService {
    @Autowired
    @Qualifier(value="staffRepository")
    private StaffRepository staffRepository;
    @Transactional
    public void saveStaff(Staff staff){
            staffRepository.save(staff);
    }
    public String getGetNewNum(int sectionId){
        StringBuilder newNum = this.formateNum(3,sectionId);
       return  newNum.append(formateNum(3,staffRepository.getLastNumber(sectionId))).toString();
    }
    public Staff getStaffBynameAndpassAndadmin(String name,String pass,int admin){
        return staffRepository.getStaffBynameAndpassAndadmin(name,pass,admin);
    }
    private StringBuilder formateNum(int count,int number){    //格式化数字
            StringBuilder newNumber = new StringBuilder();
            int length = newNumber.append(number).length();
            if(length < count){
                for(int i = 0; i < count - length; i++){
                    newNumber.insert(0,"0");
                }
            }
            return newNumber;
    }
    @Transactional
    public void updateStaffNameById(Map map) throws Exception{
        staffRepository.updateStaffNameById((String) map.get("newName"), (String)map.get("staffId"));
    }
    public Boolean judgeHaveUser(Map map){
        Boolean flag = false;
        if(staffRepository.findByStaffNameAndAdmin(map.get("name").toString(),Integer.parseInt(map.get("admin").toString())) != null){
            flag = true;
        }
        return flag;
    }
    @Transactional
    public void changePass(Map map){
        staffRepository.updateStaffPassById(map.get("newPass").toString(),Integer.parseInt(map.get("staffId").toString()),Integer.parseInt(map.get("admin").toString()));
    }


    public Page getAllStaff(int page,int size){
        Sort sort = new Sort(Sort.Direction.DESC,"staffId");
        Pageable pageable = new PageRequest(page,size,sort);
        return staffRepository.findByIsHiring("是",pageable);
    }



    public Page getAllStaffBySectioId(int sectionId,int page,int size){
        Sort sort = new Sort(Sort.Direction.DESC,"staffId");
        Pageable pageable = new PageRequest(page,size,sort);
        System.out.println(page);
        return staffRepository.findByIsHiringAndSection_SectionId("是",sectionId,pageable);
    }
}
