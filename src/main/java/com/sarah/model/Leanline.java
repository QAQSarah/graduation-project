package com.sarah.model;

public class Leanline {
    private Integer id;

    private String downloadurl;

    private String name;

    private Integer leanlineCategoryid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl == null ? null : downloadurl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLeanlineCategoryid() {
        return leanlineCategoryid;
    }

    public void setLeanlineCategoryid(Integer leanlineCategoryid) {
        this.leanlineCategoryid = leanlineCategoryid;
    }
}