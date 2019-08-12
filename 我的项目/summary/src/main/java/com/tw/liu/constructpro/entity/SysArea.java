package com.tw.liu.constructpro.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "sys_area")
@Data
public class SysArea {

    @Id
    private String id;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "parent_ids")
    private String parentIds;
    @Column(name = "name")
    private String name;
    @Column(name = "sort")
    private int sort;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
    @Column(name = "create_by")
    private String creatBy = "1";
    @Column(name = "create_date")
    private Date createDate = new Date();
    @Column(name = "update_date")
    private Date updateDate = new Date();
    @Column(name = "update_by")
    private String updateBy = "1";
    @Column(name = "del_flag")
    private String delFlag = "0";

    public SysArea() {
    }

    public SysArea(String parentId, String parentIds, String name, int sort, String code, String type) {
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.name = name;
        this.sort = sort;
        this.code = code;
        this.type = type;
    }
}
