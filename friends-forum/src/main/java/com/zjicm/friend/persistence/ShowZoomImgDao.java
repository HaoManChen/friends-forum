package com.zjicm.friend.persistence;

import com.zjicm.friend.domain.ShowZoomImgTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowZoomImgDao extends JpaRepository<ShowZoomImgTable,String> {
}
