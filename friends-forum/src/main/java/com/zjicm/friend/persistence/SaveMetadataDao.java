package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.SaveMetadataTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaveMetadataDao extends JpaRepository<SaveMetadataTable,String> {
    @Modifying
    @Transactional
    SaveMetadataTable save(SaveMetadataTable saveMetadataTable);

    @Query(value = "SELECT a.* FROM save_metadata_table a WHERE a.metadata_type = ? AND a.metadata_name LIKE ?",nativeQuery = true)
    List<SaveMetadataTable> findLikeTypeOrName(String type , String name);

    @Query(value = "SELECT a.* FROM save_metadata_table a GROUP BY metadata_type",nativeQuery = true)
    List<SaveMetadataTable> findAllType();
}
