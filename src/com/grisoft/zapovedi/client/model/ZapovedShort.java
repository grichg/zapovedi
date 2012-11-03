package com.grisoft.zapovedi.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ZapovedShort implements IsSerializable {
	public long id;
	public String name;
	public String comment;

	public ZapovedShort() {
		// TODO Auto-generated constructor stub
	}

	public ZapovedShort(long id, String name, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.comment = comment;
	}

}
