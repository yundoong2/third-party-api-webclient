package study.thirdpartyapiwebclient.common.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum  CustomErrorCode {
    INVALID_REQUEST_ID_ERROR(HttpStatus.NO_CONTENT,"필수 입력 값이 누락 되었습니다."),
    INVALID_REQUEST_BODY_ERROR(HttpStatus.NO_CONTENT, "Request Body 입력 값이 누락 되었습니다."),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "Bad Request Error 발생"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error 발생");

    @Getter
    private final HttpStatus status;
    @Getter
    private final String message;

    CustomErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
