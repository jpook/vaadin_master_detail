package com.kws.simple.cross.producers;

import javax.enterprise.context.Dependent;

@Dependent
public class TableDefinitionProducer<E> {

	// @Inject
	// JsonDefinitionReader<E> definitionReader;
	//
	// @Produces
	// @Dependent
	// public IMasterDefinition<E> produceMasterDefinition(InjectionPoint
	// injectionPoint) {
	// Class<?> c = injectionPoint.getMember().getDeclaringClass();
	// System.out.println("injection point : "+c.getSimpleName());
	// return new MasterDefinitionJson<E>();
	// }
}
