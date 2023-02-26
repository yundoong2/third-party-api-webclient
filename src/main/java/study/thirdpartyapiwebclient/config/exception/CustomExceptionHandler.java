package study.thirdpartyapiwebclient.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;
import study.thirdpartyapiwebclient.controller.dto.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse handlerException(CustomException e, HttpServletRequest request) {

        log.error(e.getMessage(), e);

        return CustomErrorResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BadRequestException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (!e.getMessage().isEmpty()) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getStatus(), message);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BindException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (StringUtils.hasText(e.getMessage())) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getStatus(), message);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(NoSuchElementException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (StringUtils.hasText(e.getMessage())) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getStatus(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
//        if (StringUtils.hasText(e.getMessage())) {
//            message = e.getMessage();
//        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getStatus(), message);
    }
}
