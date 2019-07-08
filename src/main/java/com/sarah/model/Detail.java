package com.sarah.model;

public class Detail {
    private Integer id;

    private String name;

    private String content;

    private Integer leanlineid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLeanlineid() {
        return leanlineid;
    }

    public void setLeanlineid(Integer leanlineid) {
        this.leanlineid = leanlineid;
    }
}