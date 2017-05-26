package com.sakha.prnewswire.dto;

import java.io.Serializable;
import java.util.Date;

public class IndustryNewsDto implements Serializable {
	
	private String id;
	private String indName ;
	private String subIndName;
	private String title;
	private String link;
	private String guid;
	private String pubDate;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
	
	@Override
	public String toString() {
		return "IndustryNewsDto [indName=" + indName + ", title=" + title + ", link=" + link + ", guid=" + guid
				+ ", pubDate=" + pubDate + ", description=" + description + "]";
	}
	public String getIndName() {
		return indName;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubIndName() {
		return subIndName;
	}
	public void setSubIndName(String subIndName) {
		this.subIndName = subIndName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
	
	
	
	
}
