package com.myRoomie.constants;

public interface PathMappings {

    String BASE = "/";
    String ALL = BASE + "all";
    String SAVE = BASE + "save";
    String UPDATE = BASE + "update";
    String ID = "id";
    String ID_PRODUCT_LOCATION = "idProductLocation";
    String ID_PARAM = BASE + "{" + ID + "}";
    String ID_PRODUCT_NAME_PARAM = BASE + "{" + ID_PRODUCT_LOCATION + "}";
    String LOGIN = BASE + "login";
    String PAGE_PARAM = "page";
    String PAGE_SIZE_PARAM = "size";
    String EMBED = "embed";
    String EXCEL = BASE + "excel";
    String EXCELL_ALL = EXCEL + ALL;
    String FROM_DATE = "from";
    String TILL_DATE = "till";
    String PHONE_NUMBER = BASE + "phoneNumber";
    String UPDATE_STATUS = BASE + "updateStatus";
    String STATUS = "status";
    String TYPE = "type";
    String FILTER = BASE + "filter";
    String ADDRESS_SEARCH = BASE + "addressSearch";

    interface HeaderValues {
        String TOKEN = "token";
    }

    interface Address {
        String BASE_MAPPING = BASE + "address";
        String BASE_MAPPING_ALL = BASE + "addressAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "addressAllInExcel";
        String ADDRESS_BY_ID = BASE + "{addressId}";
        String ID = "id";
        String CITY = BASE + "city";
        String STATE = BASE + "state";
        String COUNTRY = BASE + "country";
        String PINCODE = BASE + "pincode";
        String LAT_LONG = BASE + "latLong";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ADDRESS_ID = BASE + "dateAndAddressId";
    }

    interface Property {
        String BASE_MAPPING = BASE + "property";
        String BASE_MAPPING_ALL = BASE + "propertyAll";
        String FIND = BASE + "find";
        String BASE_MAPPING_ALL_ACTIVE = BASE + "propertyAllActive";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "propertyAllInExcel";
        String ADDRESS_BY_ID = BASE + "{propertyId}";
        String ID = "id";
        String CITY = BASE + "city";
        String FEATURED = BASE + "isFeatured";
        String LAT_LONG = BASE + "latLong";
        String PROPERTY_GENDER_TYPE = BASE + "PropertyGenderType";
        String PROPERTY_GENDER_TYPE_AND_CITY = BASE + "PropertyGenderTypeAndCity";
        String PROPERTY_TYPE = BASE + "PropertyType";
        String PROPERTY_TYPE_AND_CITY = BASE + "PropertyTypeAndCity";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_PROPERTY_ID = BASE + "dateAndPropertyId";
    }

    interface Room {
        String BASE_MAPPING = BASE + "room";
        String BASE_MAPPING_ALL = BASE + "roomAll";
        String BASE_MAPPING_ALL_ACTIVE = BASE + "roomAllActive";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "roomAllInExcel";
        String ROOM_BY_ID = BASE + "{roomId}";
        String ID = "id";
        String START_AMOUNT = BASE + "startAmount";
        String AVAILABILITY_STATUS = BASE + "availabilityStatus";
        String MAX_NO_OF_GUESTS = BASE + "maxNoOfGuests";
        String NO_OF_BEDS = BASE + "noOfBeds";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ROOM_ID = BASE + "dateAndRoomId";
    }

    interface File {
        String BASE_MAPPING = BASE + "file";
        String BASE_MAPPING_ALL = BASE + "fileAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "fileAllInExcel";
        String FILE_BY_ID = BASE + "{fileId}";
        String MULTIPLE_UPLOAD = BASE + "multipleUploads";
        String SINGLE_UPLOAD = BASE + "singleUpload";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_FILE_ID = BASE + "dateAndFileId";
        String FILE_SECURE = BASE + "fileSecure";
    }

    interface Charges {
        String BASE_MAPPING = BASE + "charges";
        String BASE_MAPPING_ALL = BASE + "chargesAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "chargesAllInExcel";
        String Charges_BY_ID = BASE + "{chargesId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_CHARGES_ID = BASE + "dateAndChargesId";
    }

    interface RoomVariant {
        String BASE_MAPPING = BASE + "roomVariant";
        String BASE_MAPPING_ALL = BASE + "roomVariantAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "roomVariantAllInExcel";
        String ROOM_VARIANT_BY_ID = BASE + "{roomVariantId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ROOM_VARIANT_ID = BASE + "dateAndRoomVariantId";
    }

    interface RoomAmenity {
        String BASE_MAPPING = BASE + "roomAmenity";
        String BASE_MAPPING_ALL = BASE + "roomAmenityAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "roomAmenityAllInExcel";
        String ROOM_AMENITY_BY_ID = BASE + "{roomAmenityId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ROOM_AMENITY_ID = BASE + "dateAndRoomAmenityId";
    }

    interface RoomAmenityMapper {
        String BASE_MAPPING = BASE + "roomAmenityMapper";
        String BASE_MAPPING_ALL = BASE + "roomAmenityMapperAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "roomAmenityMapperAllInExcel";
        String ROOM_AMENITY_MAPPER_BY_ID = BASE + "{roomAmenityMapperId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ROOM_AMENITY_MAPPER_ID = BASE + "dateAndRoomAmenityMapperId";
    }

    interface CouponCode {
        String BASE_MAPPING = BASE + "couponCode";
        String BASE_MAPPING_ALL = BASE + "couponCodeAll";
        String COUPON_CODE_NAME = BASE + "couponCodeName";
        String BASE_MAPPING_ALL_ACTIVE = BASE + "couponCodeAllActive";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "couponCodeAllInExcel";
        String COUPON_CODE_BY_ID = BASE + "{couponCodeId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_COUPON_CODE_ID = BASE + "dateAndCouponCodeId";
    }

    interface Transaction {
        String BASE_MAPPING = BASE + "transactions";
        String BASE_MAPPING_ALL = BASE + "transactionsAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "transactionsAllInExcel";
        String TRANSACTION_BY_ID = BASE + "{transactionId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_TRANSACTION_ID = BASE + "dateAndTransactionId";
    }

    interface User {
        String BASE_MAPPING = BASE + "users";
        String BASE_MAPPING_ALL = BASE + "usersAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "usersAllInExcel";
        String USER_ID = BASE + "{userId}";
        String USER_EMAIL = BASE + "email";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_USER_ID = BASE + "dateAndUserId";
    }

    interface ScheduledVisits {
        String BASE_MAPPING = BASE + "scheduledVisits";
        String BASE_MAPPING_ALL = BASE + "scheduledVisitsAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "scheduledVisitsAllInExcel";
        String SCHEDULED_VISITS_ID = BASE + "{scheduledVisitsId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_SCHEDULED_VISITS_ID = BASE + "dateAndScheduledVisitsId";
    }

    interface Search {
        String BASE_MAPPING = BASE + "search";
        String SEARCH = BASE + "searchByString";
        String ID = "id";
        String GET_PROPERTY = BASE + "getProperty";
        String CITY = BASE + "city";
        String STATE = BASE + "state";
        String COUNTRY = BASE + "country";
        String PINCODE = BASE + "pincode";
        String LAT_LONG = BASE + "latLong";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_ADDRESS_ID = BASE + "dateAndAddressId";
    }

    interface ContactUs {
        String BASE_MAPPING = BASE + "contactUs";
        String BASE_MAPPING_ALL = BASE + "contactUsAll";
        String CONTACT_US_BY_ID = BASE + "{contactUsId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_CONTACT_US_ID = BASE + "dateAndContactUsId";
    }

    interface NearByPlaces {
        String BASE_MAPPING = BASE + "nearByPlaces";
        String BASE_MAPPING_ALL = BASE + "nearByPlacesAll";
        String NEAR_BY_PLACES_BY_ID = BASE + "{nearByPlacesId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_NEAR_BY_PLACES_ID = BASE + "dateAndNearByPlacesId";
    }

    interface PropertyTransaction {
        String BASE_MAPPING = BASE + "propertyTransactions";
        String BASE_MAPPING_ALL = BASE + "transactionsAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "transactionsAllInExcel";
        String TRANSACTION_BY_ID = BASE + "{transactionId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String TRANSACTION_SUCCESS = BASE + "transactionSuccess";
        String DATE_AND_TRANSACTION_ID = BASE + "dateAndTransactionId";
    }

    interface RequestCallBack {
        String BASE_MAPPING = BASE + "requestCallBack";
        String BASE_MAPPING_ALL = BASE + "requestCallBackAll";
        String BASE_MAPPING_ALL_EXCEL = BASE_MAPPING + "requestCallBackAllInExcel";
        String REQUEST_CALL_BACK_BY_ID = BASE + "{requestCallBackId}";
        String ID = "id";
        String SAVE_LIST = "/saveList";
        String CREATED = BASE + "created";
        String DATE_AND_REQUEST_CALL_BACK_ID = BASE + "dateAndRequestCallBackId";
    }
}
