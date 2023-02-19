package study.thirdpartyapiwebclient.config.exception;

import lombok.*;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private CustomErrorCode status;
    private String statusMessage;
}
