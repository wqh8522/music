package com.wqh.music.baidu.controller;

import com.wqh.music.baidu.domain.Artist;
import com.wqh.music.baidu.service.ArtistService;
import com.wqh.music.baidu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"artist"})
@Api(value="歌手Controller", tags={"歌手接口"})
public class ArtistController
{
    @Autowired
    private ArtistService artistService;

    @ApiOperation("获取歌手列表")
    @ApiImplicitParam
    @GetMapping({"list"})
    public ResultVo getAllArtist()
            throws IOException
    {
        ClassPathResource classPathResource = new ClassPathResource("static/artist.html");
        Map<String, List<Artist>> result = this.artistService.getAllArtistFromFile(classPathResource.getInputStream());
        List<Artist> topArtist = this.artistService.getTopArtist();
        result.put("TOP", topArtist);
        return new ResultVo(result);
    }
}
