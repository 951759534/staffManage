package org.staffManage.Model.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.staffManage.Model.Enitity.Section;
import org.staffManage.Model.Enitity.Staff;

/**
 * Created by K550jk on 2017/6/20.
 */
@Repository
public interface StaffRepository extends CrudRepository<Staff,Long>{
            @Query(value = "select count(*) from Staff s where s.section_id = ?1",nativeQuery = true)
            public int getLastNumber(int sectionId);
            @Query(value = "select * from Staff s where s.staff_name=?1 and s.staff_pass=?2 and s.admin = ?3",nativeQuery = true)
            public Staff getStaffBynameAndpassAndadmin(String name,String pass,int admin);
            @Query(value = "update staff set staff_name = ?1 where staff_id = ?2",nativeQuery = true)
            @Modifying
            public void updateStaffNameById(String name,String id);

            public Staff findByStaffNameAndAdmin(String staff_name,int admin);
            @Query(value = "update staff set staff_pass = ?1 where staff_id = ?2 and admin=?3",nativeQuery = true)
            @Modifying
            public void updateStaffPassById(String pass,int staffId,int admin);

            public Page findByIsHiring(String isHiring,Pageable pageable);

            public Page findByIsHiringAndSection_SectionId(String isHiring,Integer sectionId, Pageable pageable);

}
