package com.sakha.prnewswire.schedular;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sakha.prnewswire.service.NewsReleasesdustryMarketsService;

@Component
public class RSSNewsFeedReader {

	@Autowired
	private NewsReleasesdustryMarketsService service;
	
	//@Scheduled(cron="* * 0/12 * * *")
	public void rssNewsReader(){
		Date currentDate = new Date();
		System.out.println("Running Job : "+currentDate);
		//service.extractCalssRelatedNews();
	}
}
