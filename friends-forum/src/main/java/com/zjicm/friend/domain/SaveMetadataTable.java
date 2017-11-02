package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "save_metadata_table")
public class SaveMetadataTable {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name="myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String id;

    @Column(name="metadata_type")
    private String metadataType;

    @Column(name="metadata_name")
    private String metadataName;

    @Column(name="img_url")
    private String imgUrl;

}
