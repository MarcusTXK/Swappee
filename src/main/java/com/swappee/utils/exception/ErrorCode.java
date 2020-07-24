package com.swappee.utils.exception;

/**
 * Error Codes for DAO layer
 */
public class ErrorCode {
    public static final String DB_ERROR_CREATE_FAILED = "DB_SWAPPEE_00";
    public static final String DB_ERROR_GET_ONE_FAILED = "DB_SWAPPEE_01";
    public static final String DB_ERROR_UPDATE_FAILED = "DB_SWAPPEE_02";
    public static final String DB_ERROR_DELETE_FAILED = "DB_SWAPPEE_03";
    public static final String DB_ERROR_GENERIC = "DB_SWAPPEE_04";
    public static final String DB_ERROR_GET_LIST_FAILED = "DB_SWAPPEE_05";
    public static final String DB_ERROR_GET_PAGE_FAILED = "DB_SWAPPEE_06";
    public static final String DB_ERROR_CREATE_LIST_FAILED = "DB_SWAPPEE_07";
    public static final String DB_ERROR_DELETE_LIST_FAILED = "DB_SWAPPEE_08";

    private ErrorCode() {
    }
}
