package study.thirdpartyapiwebclient.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;

public class CustomException extends RuntimeException{
    @Getter
    private HttpStatus status;
    @Getter
    private String message;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.status = customErrorCode.getStatus();
        this.message = customErrorCode.getMessage();
    }
}
