package org.staffManage.Model.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.staffManage.Model.Enitity.Section;

import java.util.List;

/**
 * Created by K550jk on 2017/6/20.
 */
@Repository
public interface SectionRepository extends CrudRepository<Section,Long> {
    public Section findBysectionId(int sectionId);

    public Page<Section> findAll(Pageable pageable);

    @Query(value = "DELETE from section where section_id = ?1",nativeQuery = true)
    @Modifying
    public void removeSectionById(int id);

    @Query(value = "select * from section where section_name = ?1",nativeQuery = true)
    public Section getSectionByName(String sectionName);
    public List findAll();


}
