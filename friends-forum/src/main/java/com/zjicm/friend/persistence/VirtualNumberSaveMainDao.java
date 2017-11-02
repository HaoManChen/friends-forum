package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.VirtualNumberSaveMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VirtualNumberSaveMainDao extends JpaRepository<VirtualNumberSaveMain,String>{
    @Modifying
    @Transactional
    VirtualNumberSaveMain save(List<VirtualNumberSaveMain> virtualNumberSaveMain);

    VirtualNumberSaveMain findFirstByIsDelete(String isDelete);

    @Transactional
    public int deleteAllByIsDelete(String isDelete);

    @Modifying
    @Transactional
    @Query(value = "update virtual_number_save_main set is_delete = ? WHERE virtual_number = ? ",nativeQuery = true)
    void updateIsDeleteByVirtualNumber(String isDelete ,String virtualNumber);


}
