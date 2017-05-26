package com.sakha.prnewswire.dao.mongo;

import com.sakha.prnewswire.dto.IndustryNewsDto;

public interface RSSNewsFeedService {
	
	public String COLLECTION = "RSSNewsFeedExtractor";
	
	public void saveExtractedDate(IndustryNewsDto extractedData);
}
