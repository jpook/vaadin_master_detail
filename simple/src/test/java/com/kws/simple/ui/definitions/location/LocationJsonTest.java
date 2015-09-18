package com.kws.simple.ui.definitions.location;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.kws.simple.view.definitions.MasterDefinitionJson;
import com.kws.simple.view.definitions.interf.IMasterDefinition;

public class LocationJsonTest {

	final String path = "src\\main\\resources\\";
	final String pckg = "com\\kws\\simple\\ui\\definitions\\location\\";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Gson gson = new Gson();
		File file = new File(path+pckg+"location.json");
		IMasterDefinition lj = null;
		System.out.println("Path:\t\t"+file.getPath());
		System.out.println("Absolute Path:\t"+file.getAbsolutePath());
		System.out.println("Exists:\t"+file.exists());
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			lj = gson.fromJson(br, MasterDefinitionJson.class);
			
			System.out.println(lj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(lj);
		
		
	}

}
