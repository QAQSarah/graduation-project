package com.sarah.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String exmail;

    private String imgurl;

    private Date createtime;

    private Integer status;
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getExmail() {
        return exmail;
    }

    public void setExmail(String exmail) {
        this.exmail = exmail == null ? null : exmail.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}