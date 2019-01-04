package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author xboat date 2019-01-04
 */
@Component
@ConfigurationProperties("book")
public class BookProperties  {
    private String name;

    private Integer page;

    private Boolean stauts;

    private Date date;

    private List<String> lists= new ArrayList<>();

    private Map<String, String> maps = new HashMap<>();

    private ImageInfo imageInfo = new ImageInfo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getStatus() {
        return stauts;
    }

    public void setStauts(Boolean stauts) {
        this.stauts = stauts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> lists) {
        this.maps = maps;
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setMaps(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    public class ImageInfo{
        private Integer height;
        private Integer width;

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

    }

}
