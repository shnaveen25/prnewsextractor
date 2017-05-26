package com.sakha.prnewswire.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public final class ZinnovAdminPortalClient {

	private static MongoClient mongoClient;

	private static final String MONGO_HOST = "127.0.0.1";

	private static final int MONGO_PORT = 27017;

	private static final String MONGO_ZINNOV_DB = "ZinnovAdminPortal";

	private static final Logger logger = LoggerFactory.getLogger(ZinnovAdminPortalClient.class);

	private ZinnovAdminPortalClient() {

	}

	/**
	 * The static method user to instantiate the mongo connection
	 * 
	 * @author naveen 
	 * @return
	 */
	public static MongoClient getMongoClient() {
		if (mongoClient != null) {
			return mongoClient;
		}
		try {
			mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
			logger.info("Mongo client has been instantiated");
		} catch (Exception e) {
			logger.error("Error while instantiating mongo client", e);
		}

		return mongoClient;
	}
	
	
	/**
	 * 
	 * The static method to instantiate mongoDb
	 * 
	 * @author naveen
	 * @return
	 */
	public static DB getDatabase(){
		
		if(mongoClient == null) {
			getMongoClient();
		}
		
		DB database = null;
		
		try{
			database = mongoClient.getDB(MONGO_ZINNOV_DB);
			
			logger.info("Database instantiated" );
			
		}catch (Exception e) {
			logger.error("Error while instantiating mongo database", e);
		}
		
		return database;
	}
	
	/**
	 * 
	 * @author naveen
	 * @param collecionName
	 * @return
	 * 
	 */
	public static DBCollection getCollection(String collecionName){
		DB database  = getDatabase();
		
		if(database == null){
			logger.error("No database found");
			return null;
		}
		
		DBCollection collection = null;
		
		try{
			collection = database.getCollection(collecionName);
		}catch (Exception e) {
			logger.error("Error while instantiating mongo collection", e);
		}
		return collection;
	}
	
	public static void closeMongoClient(){
		
		if(mongoClient == null){
			logger.warn("Trying to close mongo which hassent been instantiated");
			
			return;
		}
		
		logger.info("Mongo client has been terminated");
		mongoClient.close();
		return;
	}
}
