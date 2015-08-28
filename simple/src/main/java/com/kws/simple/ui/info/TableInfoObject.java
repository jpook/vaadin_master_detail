package com.kws.simple.ui.info;

import java.io.Serializable;

public class TableInfoObject implements Serializable {

	private static final long serialVersionUID = -8558590893838304947L;
	Integer order;
	String header;

	public TableInfoObject(Integer order, String header) {
		super();
		this.order = order;
		this.header = header;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

}
