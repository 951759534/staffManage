package org.staffManage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.staffManage.Model.Enitity.Section;
import org.staffManage.Model.Service.SectionService;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by K550jk on 2017/6/20.
 */
@Controller
@RequestMapping("/section")
public class SectionController {
    @Autowired
    @Qualifier(value="sectionService")
    private SectionService sectionService;

    @RequestMapping("/saveSection")
    @ResponseBody
    public String saveSection(@RequestParam("section")String sectionName){
        String flag = "fail";
        try {
            Section section = new Section();
            section.setSectionName(sectionName);
            section.setBuildTime(new Date());
            sectionService.saveSection(section);
            flag = "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    @RequestMapping("/getAllSection")
    @ResponseBody
    public List getAllSection(){
        List allSection = sectionService.findAllSections();
        Collections.reverse(allSection);
        return allSection;
    }
}
