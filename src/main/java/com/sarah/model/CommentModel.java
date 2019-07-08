package com.sarah.model;

import java.util.List;

public class CommentModel {
	
	private int id;
	private String fuser;
	private int aritcleId;
	private String comtext;
	private String time;
	private List<ReponseModel> reponses;
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
	/**
	 * @return the reponses
	 */
	public List<ReponseModel> getReponses() {
		return reponses;
	}
	/**
	 * @param reponses the reponses to set
	 */
	public void setReponses(List<ReponseModel> reponses) {
		this.reponses = reponses;
	}
	/**
	 * @return the fuser
	 */
	public String getFuser() {
		return fuser;
	}
	/**
	 * @param fuser the fuser to set
	 */
	public void setFuser(String fuser) {
		this.fuser = fuser;
	}
	/**
	 * @return the aritcleId
	 */
	public int getAritcleId() {
		return aritcleId;
	}
	/**
	 * @param aritcleId the aritcleId to set
	 */
	public void setAritcleId(int aritcleId) {
		this.aritcleId = aritcleId;
	}
	/**
	 * @return the comtext
	 */
	public String getComtext() {
		return comtext;
	}
	/**
	 * @param comtext the comtext to set
	 */
	public void setComtext(String comtext) {
		this.comtext = comtext;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
}
