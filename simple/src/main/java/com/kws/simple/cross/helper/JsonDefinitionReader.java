package com.kws.simple.cross.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.kws.simple.view.definitions.DetailDefinitionJson;
import com.kws.simple.view.definitions.MasterDefinitionJson;
import com.kws.simple.view.definitions.interf.IDetailDefinition;
import com.kws.simple.view.definitions.interf.IMasterDefinition;

@Dependent
public class JsonDefinitionReader<E> implements IJpoDefinitionReader<E> {

	private static final long serialVersionUID = -1706369996373334434L;

	Gson gson = new Gson();
	Class<?> clazz;

	@Inject
	Logger log;

	@SuppressWarnings("unchecked")
	public IMasterDefinition<E> loadMaster(Class<?> clazz) {
		return (IMasterDefinition<E>) load(clazz,
				MasterDefinitionJson.class, clazz.getSimpleName().toLowerCase()
						+ "Master");
	}

	@SuppressWarnings("unchecked")
	public IDetailDefinition<E> loadDetail(Class<?> clazz) {
		return (IDetailDefinition<E>) load(clazz,
				DetailDefinitionJson.class, clazz.getSimpleName().toLowerCase()
						+ "Detail");
	}

	private Object load(Class<?> clazz, Class<?> returnClazz, String name) {
		String fn = // "/" +
		name + ".json";
		try {
			log.info("loading resource :"+fn);
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fn);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			Object tdj = gson.fromJson(br, returnClazz);
			return tdj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
