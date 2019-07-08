package com.sarah.service.impl;

import java.util.Date;

public class ReponseVo {

	private Integer id;

	private String content;

	private String commentContext;

	private String createtime;

	private String fromUser;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the commentContext
	 */
	public String getCommentContext() {
		return commentContext;
	}

	/**
	 * @param commentContext the commentContext to set
	 */
	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	/**
	 * @return the createtime
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the fromUser
	 */
	public String getFromUser() {
		return fromUser;
	}

	/**
	 * @param fromUser the fromUser to set
	 */
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
}
