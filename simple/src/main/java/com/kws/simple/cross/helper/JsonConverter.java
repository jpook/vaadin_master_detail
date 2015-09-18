package com.kws.simple.cross.helper;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Singleton
public class JsonConverter {

	GsonBuilder gsonBuilder = new GsonBuilder();
	Gson gson = new Gson();

	@PostConstruct
	public void init() {
		
	}
	
}
