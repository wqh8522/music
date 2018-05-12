package com.wqh.music.baidu.service.impl;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.wqh.music.baidu.domain.Artist;
import com.wqh.music.baidu.service.ArtistService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Override
    public List<Artist> getAllArtist() throws IOException {
        List<Artist> artists = Lists.newArrayList();

        File input = ResourceUtils.getFile("classpath:artist.html");
        Document document = Jsoup.parse(input, "UTF-8", "http://music.baidu.com/artist");
        Elements href = document.getElementsByClass("list-item");
        System.out.println(href.size());
        for (Element element : href) {
            String linkHref = element.attr("href");
            String linkTitle = element.attr("title");
            String linkText = element.text();
            if ((StringUtils.isNotBlank(linkHref)) && (StringUtils.isNotBlank(linkTitle)) && (StringUtils.isNotBlank(linkText))) {
                Artist artist = new Artist();
                artist.setId(linkHref.replaceAll("/artist/", ""));
                artist.setName(linkTitle);
                artists.add(artist);
            }
        }
        return artists;
    }

    @Override
    public Map<String, List<Artist>> getAllArtistFromFile(InputStream inputStream) throws IOException {
        Document document = Jsoup.parse(inputStream, "UTF-8", "http://music.baidu.com/artist");

        Elements uls = document.getElementsByClass("list-item");
        Map<String, List<Artist>> result = HashBiMap.create();
        for (Element ul : uls)
        {
            List<Artist> artists = Lists.newArrayList();

            Elements as = ul.getElementsByAttributeValueStarting("href", "/artist/");
            Element h3 = ul.getElementsByTag("h3").first().child(0);
            for (Element a : as)
            {
                Artist artist = new Artist();
                String linkHref = a.attr("href").replaceAll("/artist/", "");
                artist.setId(linkHref);
                artist.setName(a.text());
                artists.add(artist);
            }
            result.put(h3.attr("name"), artists);
        }
        return result;
    }

    @Override
    public List<Artist> getTopArtist() throws IOException {
        List<Artist> artists = Lists.newArrayList();
        Document document = Jsoup.connect("http://music.baidu.com/artist").get();
        Elements jsopu = document.getElementsByClass("hot-head");
        for (Element element : jsopu)
        {

            Elements href = element.getElementsByAttributeValueStarting("href", "/artist/");
            for (Element e : href)
            {
                String linkHref = "";
                String linkTitle = "";
                String src = "";
                Elements childrens = e.children();
                for (Element c : childrens)
                {
                    linkHref = e.attr("href");
                    linkTitle = c.attr("title");
                    Elements img = c.getElementsByTag("img");
                    for (Element i : img) {
                        src = i.attr("src");
                    }
                    Artist artist = new Artist();
                    artist.setName(linkTitle);
                    artist.setId(linkHref.replaceAll("/artist/", ""));
                    artist.setPic(src);
                    artists.add(artist);
                }
            }
        }

        return artists;
    }
}
