package com.swappee.utils.exception;

public class BaseServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    private static final String SERVICE_ERROR_MESSAGE_NULL = "Unknown Service Error";
    private static final String SERVICE_ERROR_CODE_NULL = "service.error.code.unknown";

    private String serviceErrorMessage;

    private String serviceErrorCode;

    // displays service level error message
    public BaseServiceException(String serviceErrorMessage) {
        super(serviceErrorMessage);
        this.serviceErrorMessage = serviceErrorMessage;
    }

    // displays service level custom error message
    public BaseServiceException(String customExceptionMessage, String serviceErrorMessage) {
        super(customExceptionMessage);
        this.serviceErrorMessage = serviceErrorMessage;
    }

    public BaseServiceException(String serviceErrorMessage, Throwable cause) {
        super(serviceErrorMessage, cause);
        this.serviceErrorMessage = serviceErrorMessage;
    }

    public BaseServiceException(String customExceptionMessage, String serviceErrorMessage, Throwable cause) {
        super(customExceptionMessage, cause);
        this.serviceErrorMessage = serviceErrorMessage;
    }

    public BaseServiceException(String customExceptionMessage, String serviceErrorMessage, String serviceErrorCode, Throwable cause) {
        super(customExceptionMessage, cause);
        this.serviceErrorMessage = serviceErrorMessage;
        this.serviceErrorCode = serviceErrorCode;
    }

    public String getServiceErrorMessage() {
        return serviceErrorMessage == null ? SERVICE_ERROR_MESSAGE_NULL : serviceErrorMessage;
    }

    public void setServiceErrorMessage(String serviceErrorMessage) {
        this.serviceErrorMessage = serviceErrorMessage;
    }

    public String getServiceErrorCode() {
        return serviceErrorCode == null ? SERVICE_ERROR_CODE_NULL : serviceErrorCode;
    }

    public void setServiceErrorCode(String serviceErrorCode) {
        this.serviceErrorCode = serviceErrorCode;
    }
}
