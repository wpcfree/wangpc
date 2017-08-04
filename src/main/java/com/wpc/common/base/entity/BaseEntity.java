package com.wpc.common.base.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}