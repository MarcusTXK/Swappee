package com.swappee.utils.exception;

/**
 * Custom Exception
 * daoErrorMessage - Error Code.
 */
public class BaseDaoException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final String DAO_ERROR_MESSAGE_NULL = "Unknown DAO Error";
    private final String daoErrorMessage;

    public BaseDaoException(String daoErrorMessage, Throwable cause) {
        super(daoErrorMessage, cause);
        this.daoErrorMessage = daoErrorMessage;
    }

    public String getDaoErrorMessage() {
        return daoErrorMessage == null ? DAO_ERROR_MESSAGE_NULL : daoErrorMessage;
    }

}
