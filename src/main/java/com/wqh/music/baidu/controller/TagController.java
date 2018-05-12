package com.wqh.music.baidu.controller;

import com.wqh.music.baidu.domain.Tag;
import com.wqh.music.baidu.service.TagService;
import com.wqh.music.baidu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping({"tag"})
@Api(value = "歌曲分类Controller", tags = {"分类接口"})
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("根据分类名获取分类歌曲列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "tag", value = "分类名", dataType = "String", paramType = "path", required = true),
            @ApiImplicitParam(name = "start", value = "列表开始索引，从0开始，查询结果不包括该索引！", dataType = "Integer", paramType = "path", required = true)})
    @GetMapping({"songs/{tag}/{start}"})
    public ResultVo getTagAongList(@PathVariable("tag") String tag, @PathVariable("start") Integer start)
            throws IOException {
        if ((null == start) || (0 == start.intValue())) {
            start = Integer.valueOf(0);
        }
        String encode_tag = URLEncoder.encode(tag, "UTF-8");
        String url = "http://music.baidu.com/tag/" + encode_tag + "?start=" + start + "&size=20&third_type=0";
        Tag result = this.tagService.getSongListByTagName(url);
        result.setStart(start);
        result.setName(tag);
        return new ResultVo(result);
    }
}
