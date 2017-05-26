package com.sakha.prnewswire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakha.prnewswire.service.NewsReleasesdustryMarketsService;

@RestController
@RequestMapping("/newsrelaeses")
public class NewsReleasesdustryMarketsController {

	@Autowired
	private NewsReleasesdustryMarketsService service;
	
	@RequestMapping("/newsbysubindustry")
	public void runNewsRelasesHarvester(){
		service.extractCalssRelatedNews();
	}
}
