package com.sakha.prnewswire.service.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sakha.prnewswire.service.AngelListService;

@Service("AngelListService")
public class AngelLIstServiceImpl implements AngelListService {

	private static final Logger logger = LoggerFactory.getLogger(AngelLIstServiceImpl.class);
	
	private String type = "";
	//private String date = "";
	private String raisedValue= "";
	private String linkedOnRaisedValue= "";
	private String valuvation= ""; 
	
	
	WebDriver htmlClient = new HtmlUnitDriver();
	
	//WebClient client = new WebClient();

	String url = "https://angel.co/";
	
	@Override
	public void getCompanyDetails(String companyName) throws Exception {

		htmlClient.get(url+companyName);
		logger.info("Requested URl : "+url+companyName);
		
		WebElement ulOfFunds = htmlClient.findElement(By.className("startup_rounds.with_rounds"));
		
		List<WebElement> liOfFunds = ulOfFunds.findElements(By.tagName("li"));
		
		for(WebElement eachLI : liOfFunds){
			WebElement sections = eachLI.findElement(By.className("show section"));
			
			List<WebElement> listOfDivisions = sections.findElements(By.tagName("div"));
			
			for(WebElement eachSection : listOfDivisions){
				
				List<WebElement> innerDiv = eachSection.findElements(By.tagName("div"));
				
				for(WebElement eachInnerDiv : innerDiv){
					//System.out.println(eachInnerDiv.getText());
					
					type = eachInnerDiv.findElement(By.xpath("//div[contains(@class, 'type')]")).getText();
					String date = eachInnerDiv.findElement(By.xpath("//div[contains(@class, 'date_display')]")).getText();
					System.out.println("Type : "+type);
					System.out.println("Date : "+date);
				}
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
/*		
 		HtmlPage companyProfile =  client.getPage(url+companyName);
		 
		List<HtmlDivision> divTag = (List<HtmlDivision>) companyProfile.getByXPath("//div[@class='past_financing section']");
		
		//List<HtmlElement> fundingliElement = divTag.get(0).getElementsByAttribute("li", "class", "not_editing startup_round");
		
		System.out.println("Title : "+companyProfile.getTitleText());
		System.out.println("The Div tag : "+companyProfile.getByXPath("//div[@class='page']"));
		
		//System.out.println("The size of  Ul : "+fundingliElement);
*/			
	}

}
