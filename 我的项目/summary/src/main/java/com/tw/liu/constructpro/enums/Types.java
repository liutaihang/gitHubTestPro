package com.tw.liu.constructpro.enums;

public enum Types {
    demo, test;

    public static boolean hasEnum(String name){
        boolean tem = false;
        for (Types t : Types.values()) {
            if(t.name().equals(name)){
                tem = true;
                break;
            }
        }
        return tem;
    }
}
