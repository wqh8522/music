package com.wqh.music.baidu.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public class WebUtil {
    private static final Logger log = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 发送get请求
     * @param url  请求地址
     * @return
     * @throws IOException
     */
    public static String get(String url)
            throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        int i = httpClient.executeMethod(getMethod);
        log.info("请求结果{}", Integer.valueOf(i));
        String s = new String(getMethod.getResponseBodyAsString().getBytes(Charset.forName("ISO-8859-1")));
        return s;
    }

    /**
     * 使用jsoup发送请求，并返回指定的Elements
     * @param url 请求地址
     * @param key 元素，如a标签
     * @param preValue 值
     * @return
     */
    public static Elements getJsopu(String url, String key, String preValue) {
        Document document = null;
        Elements href = null;
        try {
            document = Jsoup.connect(url).get();
            href = document.getElementsByAttributeValueStarting(key, preValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return href;
    }

    /**
     * 使用jsoup发送请求，并根据指定类返回指定的Elements
     * @param url 请求地址
     * @param className 类名，
     * @return
     */
    public static Elements getJsopu(String url, String className) {
        Document document = null;
        Elements href = null;
        try {
            document = Jsoup.connect(url).get();
            href = document.getElementsByClass(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return href;
    }
}
