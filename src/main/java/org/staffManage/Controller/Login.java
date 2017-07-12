package org.staffManage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.staffManage.Controller.loginBean.LoginParam;
import org.staffManage.Model.Enitity.Staff;
import org.staffManage.Model.Service.StaffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by K550jk on 2017/6/20.
 */
@Controller
public class Login {
    @Autowired
    @Qualifier(value = "staffService")
    private StaffService staffService;
    @RequestMapping(value = {"/","/login"})
    public String userLogin(){
        return "admin_login";
    }
    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    @ResponseBody
    public  String checkLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession session, @RequestBody LoginParam loginParam){
        String isOk = null;
        try{
            Staff sessionStaff =  staffService.getStaffBynameAndpassAndadmin(loginParam.getName(),loginParam.getPass(),loginParam.getAdmin());
             isOk = "fail";
            if(sessionStaff != null){
                isOk = "success";
                switch (loginParam.getAdmin()){
                    case 0:{
                        session.setAttribute("staff",sessionStaff);
                    }
                    break;
                    case 1:{
                        session.setAttribute("admin",sessionStaff);
                    }
                    break;
                }
            }else{
                isOk = "fail";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
    @RequestMapping("/login_out/{admin}")
    public String loginOut(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("admin") String admin,HttpSession session){
            switch (admin){
                case "0":
                {
                    session.removeAttribute("staff");
                } break;
                case "1":
                {
                    session.removeAttribute("admin");
                }default:
                    session.removeAttribute("staff");
                    session.removeAttribute("admin");
            }
                return "redirect:/login";
    }
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    @ResponseBody
    public String checkUser(@RequestBody() Map map ){
        String result = "fail";
        if(staffService.judgeHaveUser(map)){
            result = "have";
        }
        return result;
    }
}
