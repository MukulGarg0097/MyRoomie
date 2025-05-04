package com.myRoomie.constants;

public interface EntityDetails {

	public interface AddressEntity {
		public String ENTITY_NAME = "AddressEntity";
		public String ID = "id";
		public String TABLE_NAME = "addressEntity";
		public String CREATED = "created";
	}
	
	public interface PropertyEntity {
		public String ENTITY_NAME = "PropertyEntity";
		public String TABLE_NAME = "propertyEntity";
		public String NEAR_BY_PLACES = "nearByPlaces";
		public String ADDRESS = "address";
		public String CREATED = "created";
		public String IS_FEATURE_FLAGE = "isFeatureFlag";
		public String IS_ACTIVE = "isActive";
		public Class<com.myRoomie.Entities.PropertyEntity> CLASS = com.myRoomie.Entities.PropertyEntity.class;
		public String[] FIND_BY_PARAM_COLS= {"city","isActive","isFeatureFlag","propertyGenderType","propertyName","propertyType"};
		public String[] SEARCH_AND_COLS= {"sharingType","roomType","propertyGenderType","propertyType"};
		public String[] SEARCH_LIKE_COLS= {"propertyName","aboutProperty","city","locality","landmark","state","country"};
	}
	
	public interface RoomEntity {
		public String ENTITY_NAME = "RoomEntity";
		public String TABLE_NAME = "roomEntity";
		public String CREATED = "created";
	}
	
	public interface ChargesEntity {
		public String ENTITY_NAME = "ChargesEntity";
		public String TABLE_NAME = "chargesEntity";
		public String CREATED = "created";
	}
	
	public interface RoomAmenity {
		public String ENTITY_NAME = "RoomAmenityEntity";
		public String TABLE_NAME = "roomAmenityEntity";
		public String CREATED = "created";
	}
	
	public interface RoomVariant {
		public String ENTITY_NAME = "RoomVariant";
		public String TABLE_NAME = "roomVariant";
		public String CREATED = "created";
	}
	
	public interface RoomAmenitiesMapper {
		public String ENTITY_NAME = "RoomAmenitiesMapper";
		public String TABLE_NAME = "roomAmenitiesMapper";
		public String CREATED = "created";
	}
	
	public interface FileEntity {
		public String ENTITY_NAME = "FileEntity";
		public String TABLE_NAME = "fileTypes";
		public String CREATED = "created";
	}
	
	public interface CouponCodeEntity {
		public String ENTITY_NAME = "CouponCodeEntity";
		public String TABLE_NAME = "couponCodeEntity";
		public String CREATED = "created";
	}
	
	public interface TaxesEntity {
		public String ENTITY_NAME = "TaxesEntity";
		public String TABLE_NAME = "taxesEntity";
		public String CREATED = "created";
	}
	
	public interface DiscountEntity {
		public String ENTITY_NAME = "DiscountEntity";
		public String TABLE_NAME = "discountEntity";
		public String CREATED = "created";
	}
	
	public interface TransactionsEntity {
		public String ENTITY_NAME = "TransactionsEntity";
		public String TABLE_NAME = "transactionsEntity";
		public String CREATED = "created";
	}
	
	public interface PropertyTransactionsEntity {
		public String ENTITY_NAME = "PropertyTransactionsEntity";
		public String TABLE_NAME = "propertyTransactionsEntity";
		public String CREATED = "created";
	}
	
	public interface TokenEntity {
		public String ENTITY_NAME = "TokenEntity";
		public String TABLE_NAME = "tokenEntity";
		public String CREATED = "created";
	}
	
	public interface UserEntity {
		public String ENTITY_NAME = "UserEntity";
		public String TABLE_NAME = "userEntity";
		public String CREATED = "created";
	}
	
	public interface ScheduledVisitsEntity {
		public String ENTITY_NAME = "ScheduledVisitsEntity";
		public String TABLE_NAME = "scheduledVisits";
		public String CREATED = "created";
	}
	
	public interface RazorOrderEntity {
		public String ENTITY_NAME = "RazorOrderEntity";
		public String TABLE_NAME = "razorOrders";
		public String CREATED = "created";
	}
	
	public interface ContactUsEntity {
		public String ENTITY_NAME = "ContactUsEntity";
		public String TABLE_NAME = "contactUs";
		public String CREATED = "created";
	}
	
	public interface NearByPlaces{
		public String ENTITY_NAME = "NearByPlacesEntity";
		public String ID = "id";
		public String TABLE_NAME = "nearByPlaces";
		public String CREATED = "created";
	}
	
	public interface RequestCallBack{
		public String ENTITY_NAME = "RequestCallBackEntity";
		public String ID = "id";
		public String TABLE_NAME = "requestCallBackEntity";
		public String CREATED = "created";
	}
}
