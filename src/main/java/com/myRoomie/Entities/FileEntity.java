package com.myRoomie.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.FileEntity.TABLE_NAME)
public class FileEntity extends BaseCreatedEntity{

	@Column
	private String url;
	@Column
	private String type;
	@Column
	private String caption;
	@Column
	private Integer propertyId;
	@Column
	private Integer roomId;
}