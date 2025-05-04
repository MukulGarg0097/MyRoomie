package com.myRoomie.Entities;

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
@Table(name=EntityDetails.TransactionsEntity.TABLE_NAME)
public class TransactionEntity extends BaseCreatedEntity {

	@Column
	private Integer roomId;
	@Column
	private Integer roomVariantId;
	@Column
	private Double roomAmount;
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
	@JoinColumn(name = "transactionId")
	private Set<DiscountEntity> discounts;
	
	@OneToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "transactionId")
	private Set<TaxesEntity> taxes;
}
