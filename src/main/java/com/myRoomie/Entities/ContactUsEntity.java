package com.myRoomie.Entities;

import com.myRoomie.constants.EntityDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name=EntityDetails.ContactUsEntity.TABLE_NAME)
public class ContactUsEntity  extends BaseCreatedEntity{

	@Column
	private String email;
	@Column
	private String name;
	@Column(columnDefinition="varchar(1000) DEFAULT NULL")
	private String message;
	@Column(columnDefinition="varchar(1000) DEFAULT 'sdd'")
	private Integer da;
	@Column
	private String phone;
    @Column
    private String looking;
}
