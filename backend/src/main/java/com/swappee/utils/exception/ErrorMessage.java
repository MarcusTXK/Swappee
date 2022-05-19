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
    public static final String USER_ERROR_REVIEW_FAILED = "Error in review user service";

    //Item Error Message
    public static final String ITEM_ERROR_CREATE_FAILED = "Error in create item service";
    public static final String ITEM_ERROR_UPDATE_FAILED = "Error in update item service";
    public static final String ITEM_ERROR_DELETE_FAILED = "Error in delete item service";
    public static final String ITEM_ERROR_GET_ONE_FAILED = "Error in get item service";
    public static final String ITEM_ERROR_GET_LIST_FAILED = "Error in get list item service";
    public static final String ITEM_ERROR_GET_PAGE_FAILED = "Error in get page item service";
    public static final String ITEM_ERROR_LIKE_FAILED = "Error in like item service";

    //Request Error Message
    public static final String REQUEST_ERROR_CREATE_FAILED = "Error in create request service";
    public static final String REQUEST_ERROR_UPDATE_FAILED = "Error in update request service";
    public static final String REQUEST_ERROR_HIDE_FAILED = "Error in hide request service";
    public static final String REQUEST_ERROR_GET_PAGE_FAILED = "Error in get page request service";

    public static final String REQUEST_ERROR_GET_ONE_FAILED = "Error in get request service";

    //Picture Error Message
    public static final String PICTURE_ERROR_GET_ONE_FAILED = "Error in get picture service";
    public static final String PICTURE_ERROR_GET_LIST_FAILED = "Error in get list picture service";
    public static final String PICTURE_ERROR_CREATE_FAILED = "Error in create picture service";
    public static final String PICTURE_ERROR_UPDATE_FAILED = "Error in update picture service";

    //Generic Error Message
    public static final String SVC_ERROR_GENERIC = "Error in generic service";

    //Picture Util Error Message
    public static final String PICTURE_UTIL_ERROR_PROCESS_FAILED = "Error in processing picture util";

    private ErrorMessage() {
    }
}
