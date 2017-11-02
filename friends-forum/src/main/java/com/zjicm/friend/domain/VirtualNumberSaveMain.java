package com.zjicm.friend.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "virtual_number_save_main")
public class VirtualNumberSaveMain {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name = "myGenerator" ,strategy = "com.zjicm.friend.core.jpa.MyUUIDGenerator")
    private String id;
    @Column(name = "virtual_number")
    private String virtualNumber;
    @Column(name = "is_delete")
    private String isDelete;
}
