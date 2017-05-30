package com.sakha.prnewswire.dao.mongo.impl;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.sakha.prnewswire.dao.mongo.RSSNewsFeedService;
import com.sakha.prnewswire.dto.IndustryNewsDto;
import com.sakha.prnewswire.util.ZinnovAdminPortalClient;

@Repository("RSSNewsFeedService")
public class RSSNewsFeedServiceImpl implements RSSNewsFeedService {

	private static final Logger logger = LoggerFactory.getLogger(RSSNewsFeedServiceImpl.class);

	public DBCollection dbCollection;

	public RSSNewsFeedServiceImpl() {
		this.dbCollection = ZinnovAdminPortalClient.getCollection(COLLECTION);
	}

	@Override
	public void saveExtractedDate(IndustryNewsDto extractedData) {
		BasicDBObject query = new BasicDBObject("industryName", extractedData.getIndName());
		DBObject curser = dbCollection.findOne(query);
		
		BasicDBObject isExistingSubComp = new BasicDBObject("subIndustries.link" , extractedData.getLink());
		DBObject isExist = dbCollection.findOne(isExistingSubComp);
		
		if(isExist != null){
			logger.warn("News form the link has already exists");
			return;
		}
		
		boolean addingFirstTime = false;
		
		Date currentDate =  new Date();
		
		if (curser == null) {
			curser = new BasicDBObject();
			curser.put("industryName", extractedData.getIndName());
			curser.put("createdDate", currentDate);
			addingFirstTime = true;
		}
		curser.put("modifiedDate", currentDate);
		
		BasicDBObject subIndustryObj = new BasicDBObject();
		subIndustryObj.append("subIndustryName", extractedData.getSubIndName());
		subIndustryObj.append("title", extractedData.getTitle());
		subIndustryObj.append("description", extractedData.getDescription());
		subIndustryObj.append("guid", extractedData.getGuid());
		subIndustryObj.append("link", extractedData.getLink());
		subIndustryObj.append("pubDate", extractedData.getPubDate());
		
		try {
			if(addingFirstTime){
				JSONObject sourceObj = new JSONObject();
				JSONArray subInds = new JSONArray();
				subInds.put(subIndustryObj);
				
				//logger.info("The Updated subInd and to be saved is : "+subInds);
				
				sourceObj.put("subIndustries", subInds);
				
				Object o = com.mongodb.util.JSON.parse(sourceObj.toString());
				DBObject tobeStoredSubInd = (DBObject) o;
				
				curser.putAll(tobeStoredSubInd);
				
				
				dbCollection.insert(curser);
				//logger.info("Data has been inserted/modified on : "+currentDate);
			}
			else{ 
				
				JSONObject sourceObj =  new JSONObject(curser.toString());
				JSONArray subInds = sourceObj.getJSONArray("subIndustries");
				
				//logger.info("The subInd from Collection :"+subInds);
				
				subInds.put(subIndustryObj);
				
				sourceObj =  new JSONObject();
				sourceObj.put("subIndustries", subInds);
				
				//logger.info("The Updated subInd and to be saved is : "+subInds);
				
				Object o = com.mongodb.util.JSON.parse(sourceObj.toString());
				DBObject tobeStoredSubInd = (DBObject) o;
				
				curser.putAll(tobeStoredSubInd);
								
				BasicDBObject toBeUpdated = new BasicDBObject("$set" ,curser);
				
				BasicDBObject searchQuery = new BasicDBObject("_id" , curser.get("_id"));
				
				dbCollection.updateMulti(searchQuery, toBeUpdated);
				
				logger.info("Data Has been updated : "+curser.get("_id").toString());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Internal Server Error : " + e.getMessage());
		}

	}

}
