package study.thirdpartyapiwebclient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.thirdpartyapiwebclient.services.dto.BoardDto;

import java.util.List;

/**
 * BoardExternalService 설명
 *
 * <pre>
 * - 외부 게시판 API를 호출 및 응답받기 위한 Service
 * @author cyh68
 * @since 2023-02-14
 * </pre>
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class BoardExternalService {

    private final WebClient webClient;

    /**
     * GetBoardList 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 전체 게시글을 조회한다, WebClient 사용
     * @return LIst BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-14
     * </pre>
     **/
    public Flux<BoardDto> GetBoardList() {
        Flux<BoardDto> dtoFlux = webClient.get()
                .uri("/board/list")
                .retrieve()
                .bodyToFlux(BoardDto.class);

        dtoFlux.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return dtoFlux;
    }

    /**
     * GetBoardById 설명
     *
     <pre>
     * - - 외부 게시판 API를 통해 특정 게시글을 조회한다, WebClient 사용
     * @param id {@link Long}
     * @return Mono BoardDto
     * @throw 
     * @author cyh68
     * @since 2023-02-15
     </pre>
     **/
    public Mono<BoardDto> GetBoardById(Long id) {
        Mono<BoardDto> dtoMono = webClient.get()
                .uri("/board/list/" + id)
                .retrieve()
                .bodyToMono(BoardDto.class);

        dtoMono.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return dtoMono;
    }
}
