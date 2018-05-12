package com.wqh.music.baidu.service;

import com.wqh.music.baidu.domain.Artist;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ArtistService {

    /**
     * 得到所有歌手
     * @return
     * @throws IOException
     */
    List<Artist> getAllArtist()throws IOException;

    /**
     * 从本地html文件中获取歌手信息
     * @param paramInputStream
     * @return
     * @throws IOException
     */
    Map<String, List<Artist>> getAllArtistFromFile(InputStream paramInputStream)throws IOException;

    /**
     * 得到热门歌手信息
     * @return
     * @throws IOException
     */
    List<Artist> getTopArtist()throws IOException;
}
