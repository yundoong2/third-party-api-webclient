package study.thirdpartyapiwebclient.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

import static study.thirdpartyapiwebclient.common.constants.Constants.URL;


/**
 * WebClientConfig 설명
 *
 <pre>
 * - Bean에 등록하여 WebClient를 사용할 수 있도록 클래스 생성 및 초기화
 * @author cyh68
 * @since 2023-02-15
 </pre>
 **/
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        //in-memory buffer 값이 Default로 256KB로 해당 값보다 큰 HTTP 메시지를 처리하기 위해 아래와 같이 사용
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configure -> configure.defaultCodecs().maxInMemorySize(1024*1024*50))
                .build();

        return WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", URL))
                .build();
    }
}
