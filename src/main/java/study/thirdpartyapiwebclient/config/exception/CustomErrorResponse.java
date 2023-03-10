package study.thirdpartyapiwebclient.config.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private HttpStatus status;
    private String message;
}
