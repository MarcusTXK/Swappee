package com.swappee.utils.exception;

/**
 * Error Messages for Service layer
 */
public class ErrorMessage {
    //User Error Message
    public static final String USER_ERROR_CREATE_FAILED = "Error in create user service";
    public static final String USER_ERROR_UPDATE_FAILED = "Error in update user service";
    public static final String USER_ERROR_DELETE_FAILED = "Error in delete user service";
    public static final String USER_ERROR_GET_ONE_FAILED = "Error in get user service";
    public static final String USER_ERROR_GET_LIST_FAILED = "Error in get list user service";
    public static final String USER_ERROR_GET_PAGE_FAILED = "Error in get page user service";

    //Item Error Message
    public static final String ITEM_ERROR_CREATE_FAILED = "Error in create item service";
    public static final String ITEM_ERROR_UPDATE_FAILED = "Error in update item service";
    public static final String ITEM_ERROR_DELETE_FAILED = "Error in delete item service";
    public static final String ITEM_ERROR_GET_ONE_FAILED = "Error in get item service";
    public static final String ITEM_ERROR_GET_LIST_FAILED = "Error in get list item service";
    public static final String ITEM_ERROR_GET_PAGE_FAILED = "Error in get page item service";

    //Like Error Message
    public static final String LIKE_ERROR_CREATE_FAILED = "Error in create like service";
    public static final String LIKE_ERROR_DELETE_FAILED = "Error in delete like service";
    public static final String LIKE_ERROR_GET_PAGE_FAILED = "Error in get page like service";

    //Generic Error Message
    public static final String SVC_ERROR_GENERIC = "Error in generic service";

    private ErrorMessage() {
    }
}
