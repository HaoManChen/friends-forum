package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.SaveImgTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaveImgDao extends JpaRepository<SaveImgTable,String>{
    SaveImgTable save(SaveImgTable saveImgTable);

    List<String> findImgPathByLocation(String location);

    List<SaveImgTable> findAll();

    @Query(value = "SELECT a.* FROM save_img_table a GROUP BY a.location",nativeQuery = true)
    List<SaveImgTable> groupByLocation();
}
