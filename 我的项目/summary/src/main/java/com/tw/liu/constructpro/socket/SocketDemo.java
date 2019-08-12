package com.tw.liu.constructpro.socket;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tw.liu.constructpro.entity.SysUser;

import java.util.Map;
import java.util.Set;

public class SocketDemo {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser("id", "name");
        SysUser t = new SysUser("id", "name");
        Set<SysUser> userset = Sets.newHashSet();
        Map<String, SysUser> ma = Maps.newHashMap();
        System.out.println(ma.put("sysUser", sysUser));
        System.out.println(ma.put("t", t));
        System.out.println(ma.toString());

    }
}
