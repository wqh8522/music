package com.wqh.music.baidu.util;

import com.google.common.collect.Lists;
import com.wqh.music.baidu.domain.Artist;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtil {
    public static List<Artist> regxContent(String content)
    {
        List<Artist> artists = Lists.newArrayList();

        String regex_str = "<a[^>]+>[^<]*</a>";
        Pattern pattern = Pattern.compile(regex_str);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {}
        return artists;
    }

}
