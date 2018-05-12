package com.wqh.music.baidu.service;

import com.wqh.music.baidu.domain.Tag;

import java.io.IOException;

public interface TagService {
    /**
     * 获取分类下的歌曲列表
     * @param url
     * @return
     * @throws IOException
     */
    Tag getSongListByTagName(String url)
            throws IOException;
}
