package com.myRoomie.Entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myRoomie.constants.EntityDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=EntityDetails.PropertyTransactionsEntity.TABLE_NAME)
public class PropertyTransactionEntity extends BaseCreatedEntity {

	@Column
	private String createdByName;
	@Column
	private String createdByEmail;
	@Column
	private String contactNo;
	@Column
	private Date date ;
	@Column
	private Integer propertyId;
	@Column
	private Double amount;
	@Column
	private Integer quantity;
	@Column
	private Double subTotal;
	@Column
	private Double totalDiscountAmount;
	@Column
	private Double subTotalAfterDiscount;
	@Column
	private Double totalTaxAmount;
	@Column
	private Double netTotalAmount;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "propertyTransactionId")
	private Set<DiscountEntity> discounts;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "propertyTransactionId")
	private Set<TaxesEntity> taxes;
}
