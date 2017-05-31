package com.sakha.prnewswire.service.impl;

import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sakha.prnewswire.GlobalConstants;
import com.sakha.prnewswire.dao.mongo.RSSNewsFeedService;
import com.sakha.prnewswire.dto.IndustryNewsDto;
import com.sakha.prnewswire.service.NewsReleasesdustryMarketsService;

@Service("NewsReleasesdustryMarketsService")
public class NewsReleasesdustryMarketsServImpl implements NewsReleasesdustryMarketsService {

	private static final Logger logger = LoggerFactory.getLogger(NewsReleasesdustryMarketsServImpl.class);

	WebDriver chromeDriver = null;
	@Autowired
	private RSSNewsFeedService rssNewsFeedService;

	String url = "http://www.prnewswire.com/rss/";

	String webUrl = "http://www.prnewswire.com";

	int i = 0;
	
	@Override
	public void extractCalssRelatedNews() {
		
		logger.info("Harvesting the data from " + url);

		chromeDriver = new HtmlUnitDriver();
		chromeDriver.get(url);

		WebElement table = chromeDriver.findElement(By.tagName("table"));
		List<WebElement> industry = table.findElements(By.xpath("//tbody/tr"));
		
		
		for (i = 0; i < industry.size(); i++) {

			if (industry.get(i).getText().equals(GlobalConstants.NEWS_REL_BY_IND_MAR)) {
				i++;
				while (!industry.get(i).getText().equals(GlobalConstants.PHOTO)) {
						String industryName = industry.get(i).getText();
						logger.info("The Industry Name :" + industryName);
						
						new Thread(() -> {	
							getRSSLink(industry.get(i), industryName);
						}).start();
						
						industry.get(i).click();	
	
						List<WebElement> subIndustry = industry.get(i).findElements(By.tagName("p"));
						logger.info("The industry having :" + subIndustry.size() + ": No of sub industries");
					
						//Each industry runs in seperatethread
						new Thread(() -> {	
							for (WebElement eachSubIndustry : subIndustry) {
								getRSSLink(eachSubIndustry, industryName);
							}
						}).start();
					
					i++;
				}
			}
		}
	}

	private void getRSSLink(WebElement eachSubIndustry, String industryName) {
		logger.info("Getting rss Link");
		List<WebElement> linkButton = eachSubIndustry.findElements(By.tagName("button"));
		
		if (linkButton.size() > 0) {
			String link = linkButton.get(0).getAttribute("onclick").split("=")[1];
			String rssLink = (webUrl + link.substring(1, link.length() - 1));
		
				try {
					readUrlofXmlLink(industryName, rssLink);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		}
	}

	private void readUrlofXmlLink(String industryName, String xmlFindableLink) throws Exception {
		logger.info("Click and read event of xml Link : " + xmlFindableLink);
		
		IndustryNewsDto extractedData = new IndustryNewsDto();
		Date currentDate = new Date();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new URL(xmlFindableLink).openStream());

		NodeList listOfItemTag = doc.getElementsByTagName("item");

		extractedData.setSubIndName(doc.getElementsByTagName("title").item(0).getTextContent().split(":")[1].trim());

		for (int i = 0; i < listOfItemTag.getLength(); i++) {

			Element element = (Element) listOfItemTag.item(i);
			extractedData.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
			extractedData.setLink(element.getElementsByTagName("link").item(0).getTextContent());
			extractedData.setGuid(element.getElementsByTagName("guid").item(0).getTextContent());
			extractedData.setPubDate(element.getElementsByTagName("pubDate").item(0).getTextContent());
			String description = "";
				
			WebDriver tempChromeDriver = new HtmlUnitDriver();
			tempChromeDriver.get(extractedData.getLink());
					
			WebElement releaserBody = tempChromeDriver.findElement(By.className("release-body"));
			List<WebElement> descRow = releaserBody.findElements(By.className("row"));
			for (WebElement eachRow : descRow) {
				description += eachRow.getText();
			}
			
			logger.info("SUB IND NAME : " + extractedData.getSubIndName());
			logger.info("LINK : " + extractedData.getLink());
			
			extractedData.setIndName(industryName);
			extractedData.setDescription(description);
			extractedData.setCreatedDate(currentDate);
			extractedData.setModifiedDate(currentDate);

			tempChromeDriver.close();
			
			//new Thread(() -> {			
				rssNewsFeedService.saveExtractedDate(extractedData);			
			//}).start();
		}
	}
}
