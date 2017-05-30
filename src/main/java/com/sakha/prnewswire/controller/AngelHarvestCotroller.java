package com.sakha.prnewswire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sakha.prnewswire.service.AngelListService;

@RestController
@RequestMapping(value="/angel")
public class AngelHarvestCotroller {

	@Autowired
	private AngelListService service;
	
	@RequestMapping(value="/{companyName}")
	public void getCompanyDetails(@PathVariable("companyName") String companyName){
		try {
			service.getCompanyDetails(companyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
