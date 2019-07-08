package com.sarah.model;


public class ArticleModel {
	private String title;
	
	private String content;
	
	private int id;

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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	private String auther;

	private String createtime;

	private ArticleImg articleImg;
	
	private int categoryId;
 
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the articleImg
	 */
	public ArticleImg getArticleImg() {
		return articleImg;
	}

	/**
	 * @param articleImg the articleImg to set
	 */
	public void setArticleImg(ArticleImg articleImg) {
		this.articleImg = articleImg;
	}

	

	
}
