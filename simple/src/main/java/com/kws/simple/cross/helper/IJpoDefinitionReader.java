package com.kws.simple.cross.helper;

import java.io.Serializable;

import com.kws.simple.view.definitions.interf.IDetailDefinition;
import com.kws.simple.view.definitions.interf.IMasterDefinition;

public interface IJpoDefinitionReader<E> extends Serializable {

	public IMasterDefinition<E> loadMaster(Class<?> clazz);
	public IDetailDefinition<E> loadDetail(Class<?> clazz) ;

}
