package com.sakha.prnewswire.dto;

import java.io.Serializable;
import java.util.Date;

public class IndustryNewsDto implements Serializable {
	
	private Date createdDate;
	private String description;
	private String guid;
	private String id;
	private String indName ;
	private String link;
	private Date modifiedDate;
	private String pubDate;
	private String subIndName;
	private String title;
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public String getDescription() {
		return description;
	}
	public String getGuid() {
		return guid;
	}
	public String getId() {
		return id;
	}
	public String getIndName() {
		return indName;
	}
	public String getLink() {
		return link;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public String getPubDate() {
		return pubDate;
	}
	public String getSubIndName() {
		return subIndName;
	}
	public String getTitle() {
		return title;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public void setSubIndName(String subIndName) {
		this.subIndName = subIndName;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "IndustryNewsDto [indName=" + indName + ", title=" + title + ", link=" + link + ", guid=" + guid
				+ ", pubDate=" + pubDate + ", description=" + description + "]";
	}
	
	
	
	
	
	
	
}
