package org.staffManage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.staffManage.Model.Enitity.Section;
import org.staffManage.Model.Enitity.Staff;
import org.staffManage.Model.Service.SectionService;
import org.staffManage.Model.Service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by K550jk on 2017/6/23.
 */
@Controller
@RequestMapping("/admin")
public class Admin {
    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    @RequestMapping("/index")
    public String admin_index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Map map){
        Staff admin_user = (Staff)httpServletRequest.getSession().getAttribute("admin");
        map.put("admin",admin_user.getAdmin());
        return "admin_index";
    }
    @RequestMapping("/adminName")
    public String admin_name(){
        return "admin_name";
    }
    @RequestMapping(value = "/changeName",method = RequestMethod.PUT)
    @ResponseBody
    public String changeName(@RequestBody()Map changeName,HttpSession httpSession){
        String flag = "success";
        try{
            String admin = this.getAdmin(changeName.get("admin").toString());
            Staff currentUser = (Staff)httpSession.getAttribute(admin);
            staffService.updateStaffNameById(changeName);
            currentUser.setStaffName((String) changeName.get("newName"));
            httpSession.setAttribute(admin,currentUser);
        }catch (Exception e){
            flag = "fail";
            e.printStackTrace();
        }
        return flag;
    }
    @RequestMapping("/adminLook")
    public String adminLook(){
        return "admin_LookAdmin";
    }
    @RequestMapping("/adminPass")
    public String adminPass(){
        return "admin_password";
    }
    private String getAdmin(String admin){
        String result = null;
        switch (admin){
            case "0":
                result = "staff";
            break;
            case "1":
                result = "admin";
        }
        return result;
    }
    @RequestMapping("/getOldPassResult")
    @ResponseBody
    public String getoldPassResult(@RequestBody Map map,HttpSession httpSession){
           String nowOldPass = map.get("nowOldPass").toString();
        Staff nowSession = (Staff) httpSession.getAttribute(this.getAdmin(map.get("admin").toString()));
           if(nowOldPass.equals(nowSession.getStaffPass() + "")){
               return "ok";
           }else{
               return "fail";
           }
        }
    @RequestMapping("/changePass")
    @ResponseBody
    public String changePass(@RequestBody Map map,HttpSession httpSession){
        String result = "fail";
        try{
                Staff nowSession = (Staff) httpSession.getAttribute(this.getAdmin(map.get("admin").toString()));
                map.put("staffId",nowSession.getStaffId());
                staffService.changePass(map);
                nowSession.setStaffPass(map.get("newPass").toString());
                httpSession.setAttribute(this.getAdmin(map.get("admin").toString()),nowSession);
                result = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping("/lookSection")
    public String lookSection(){
        return "admin_lookSection";
    }

    @Autowired
    @Qualifier(value = "sectionService")
    private SectionService sectionService;
    @RequestMapping(value = "/getSection",method = RequestMethod.GET)
    @ResponseBody
    public Page getSection(@RequestParam(value = "pageSize") int pageSize,@RequestParam("currentPage") int currentPage){
      Page allsections = sectionService.getSection(currentPage,pageSize);
      return allsections;
    }
    @RequestMapping(value="/removeSection",method = RequestMethod.GET)
    @ResponseBody
    public Page removeSection(@RequestParam("id") int id,@RequestParam("currentPage")int currentPage,@RequestParam("size")int size){
        sectionService.removeSection(id);
        return sectionService.getSection(currentPage,size);
    }
    @RequestMapping(value = "/getName",method = RequestMethod.GET)
    @ResponseBody
    public String getName(@RequestParam("sectionName")String sectionName){
        String flag = "Repeat";
        try{
          Section section =  sectionService.getSectionByName(sectionName);
          if(section == null){
              flag = "noRepeat";
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @RequestMapping("/lookStaff")
    public String lookStaff(){
        return "admin_lookStaff";
    }
    @RequestMapping("/getAllStaff")
    @ResponseBody
    public Page getAllStaff(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize")int pageSize){
        return staffService.getAllStaff(currentPage,pageSize);
    }

    @RequestMapping("/getStaffBySectionName")
    @ResponseBody
    public Page getStaffBySectionName(@RequestParam("sectionId") int sectionId,@RequestParam("currentPage") int currentPage,@RequestParam("size") int pageSize){
            return staffService.getAllStaffBySectioId(sectionId,currentPage,pageSize);
    }
}
