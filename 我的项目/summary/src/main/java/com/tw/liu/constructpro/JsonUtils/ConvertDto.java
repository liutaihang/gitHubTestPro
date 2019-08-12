package com.tw.liu.constructpro.JsonUtils;

import lombok.Data;

import java.util.List;

@Data
public class ConvertDto {

    private String areaId;
    private String areaName;
    private List<ConvertDto> aearList;
    private Integer sum;
    private String pid;
    private String pids;
}
