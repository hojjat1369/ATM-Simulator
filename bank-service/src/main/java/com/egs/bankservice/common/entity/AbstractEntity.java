package com.egs.bankservice.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

	public AbstractEntity() {

		super();
		createdOn = new Date();
		enable = Boolean.TRUE;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_on")
	private Date createdOn;
	private Boolean enable;

}
