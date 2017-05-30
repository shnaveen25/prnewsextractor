package com.sakha.prnewswire.schedular;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakha.prnewswire.service.NewsReleasesdustryMarketsService;

@Component
public class RSSNewsFeedReader {

	private static final Logger logger = LoggerFactory.getLogger(RSSNewsFeedReader.class);
	
	@Autowired
	private NewsReleasesdustryMarketsService service;
	
	//@Scheduled(cron="* * 0/12 * * *")
	public void rssNewsReader(){	
		Date currentDate = new Date();
		
		logger.info("Rss News Feed Schedular started at : "+currentDate);
		//service.extractCalssRelatedNews();
	}
}
