package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="save_img_table")
public class SaveImgTable {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String id;

    @Column(name="location")
    private String location;

    @Column(name="img_path")
    private String imgPath;


}
