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
import org.staffManage.Model.Repository.SectionRepository;

import java.util.List;


/**
 * Created by K550jk on 2017/6/20.
 */
@Service
public class SectionService {
    @Qualifier(value = "sectionRepository")
    @Autowired
    private SectionRepository sectionRepository;
    @Transactional
    public void saveSection(Section section){
          sectionRepository.save(section);
    }

    public Section getSectionById(int sectionId){
        return sectionRepository.findBysectionId(sectionId);
    }

    public Page<Section> getSection(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC,"sectionId");
        Pageable pages = (Pageable) new PageRequest(page,size,sort);
       return sectionRepository.findAll(pages);
    }
    @Transactional
    public void removeSection(int id){
        sectionRepository.removeSectionById(id);
    }
    public Section getSectionByName(String sectionName){
       return sectionRepository.getSectionByName(sectionName);
    }


    public List findAllSections(){
        return sectionRepository.findAll();
    }



}
