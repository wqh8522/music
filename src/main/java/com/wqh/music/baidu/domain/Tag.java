package com.wqh.music.baidu.domain;

import lombok.Data;

import java.util.List;

@Data
public class Tag {

    private String name;
    private String nums;
    private List<SongItem> songItems;
    private Integer start;
}
