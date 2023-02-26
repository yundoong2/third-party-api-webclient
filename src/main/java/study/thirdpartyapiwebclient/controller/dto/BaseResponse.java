package study.thirdpartyapiwebclient.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@ToString
/**
 * BaseResponse 설명
 *
 * - API 응답 값을 관리하기 위한 공통 객체 클래스
 * @author cyh68
 * @since 2023-02-19
 **/
public class BaseResponse implements Serializable {
    private HttpStatus status;
    private String message;
    private Object data;

    public BaseResponse() {
        this.setMessage("Success");
    }

    public BaseResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse success(Object data) {
        this.setStatus(HttpStatus.OK);
        this.setMessage("Success");
        this.setData(data);
        return this;
    }
}
