package com.leafpage.common.exception;

public class RollbackException extends RuntimeException{
    public RollbackException(String className, String methodName) {
        super(className + "의 " + methodName + " 롤백 실패");
    }
}
