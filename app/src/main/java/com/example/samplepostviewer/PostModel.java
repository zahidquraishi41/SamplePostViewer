package com.example.samplepostviewer;

import java.io.Serializable;
import java.util.List;

public class PostModel implements Serializable {
    private String title;
    private List<String> tags;
    private String url;

    public PostModel(String title, List<String> tags, String url) {
        this.title = title;
        this.tags = tags;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
