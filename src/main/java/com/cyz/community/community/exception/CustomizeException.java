package com.cyz.community.community.exception;

public class CustomizeException extends RuntimeException {
    private  String message;

<<<<<<< HEAD
    public CustomizeException(ICustomizeErrorCode errorCode){

        this.message = errorCode.getMessage();
    }

    public CustomizeException(String  message){
        this.message = message;
    }


=======
    public CustomizeException(String message){
        this.message = message;
    }

>>>>>>> 80b6fecbfaa5c93861658b8f0b106db94a5bc824
    @Override
    public String getMessage() {
        return message;
    }

}
