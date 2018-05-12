package com.wqh.music.baidu.service.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wqh.music.baidu.domain.SongItem;
import com.wqh.music.baidu.domain.Tag;
import com.wqh.music.baidu.service.TagService;
import com.wqh.music.baidu.util.WebUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public Tag getSongListByTagName(String url) throws IOException {
        List<SongItem> result = Lists.newArrayList();
        Tag tag = new Tag();
        String s = WebUtil.get(url);
        Document document = Jsoup.parse(s);
        Elements nums = document.getElementsByClass("nums");
        if (nums.size() > 0) {
            tag.setNums((nums.get(0)).text());
        }
        //得到monkey='song-list'的Elements
        Elements monkeys = document.getElementsByAttributeValue("monkey", "song-list");
        if (monkeys.size() > 0) {
            Element element = monkeys.get(0);

            Elements lis = element.getElementsByTag("li");
            for (Element li : lis) {
                String data_songitem = li.attr("data-songitem");
                JsonElement parse = new JsonParser().parse(data_songitem);
                Gson gson = new Gson();
                SongItem songItem = gson.fromJson(parse.getAsJsonObject().get("songItem"), SongItem.class);
                Elements album_spans = li.getElementsByClass("album-title");
                if (album_spans.size() > 0) {
                    //唱片信息
                    Element album_span = album_spans.get(0);
                    Elements children = album_span.children();
                    if (children.size() > 0) {
                        Element album_element = album_span.child(0);
                        songItem.setAlbum(album_element.attr("title"));
                    }
                }
                result.add(songItem);
            }
        }
        tag.setSongItems(result);
        return tag;
    }
}
