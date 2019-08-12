package com.tw.liu.constructpro.entity;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user")
public class SysUser {

    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @Column(name = "mobile")
    private String mobile;

    /**
     * 是否禁用
     */
    @Column(name = "enable", length = 2)
    private String enable;

    @Column(name = "role")
    private String role;

    @Column(name = "head_image")
    private String headImage;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    public SysUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SysUser() {
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysUser)) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(id, sysUser.id) &&
                Objects.equals(name, sysUser.name)&&
                Objects.equals(mobile, sysUser.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mobile);
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
