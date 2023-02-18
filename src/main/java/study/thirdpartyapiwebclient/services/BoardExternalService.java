package study.thirdpartyapiwebclient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
import study.thirdpartyapiwebclient.services.dto.BoardDto;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.List;
import java.util.Optional;

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
     * - 외부 게시판 API를 통해 전체 게시글을 조회한다
     * @return LIst BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-14
     * </pre>
     **/
    public List<BoardDto> GetBoardList() {
        List<BoardDto> dtoFlux = webClient.get()
                .uri("/board/list")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BoardDto.class)
                .collectList()
                .block();

        //dtoFlux.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return dtoFlux;
    }

    /**
     * GetBoardById 설명
     *
     * <pre>
     * - - 외부 게시판 API를 통해 특정 게시글을 조회한다
     * @param id {@link Long}
     * @return Mono BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-15
     * </pre>
     **/
    public BoardDto GetPostById(Long id) {
        BoardDto dtoMono = webClient.get()
                .uri("/board/list/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();

        //dtoMono.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return dtoMono;
    }

    /**
     * AddPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 등록한다
     * @param input {@link BoardInput}
     * @return BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto AddPost(BoardInput input) {
        BoardDto dtoMono = webClient.post()
                .uri("/board/list")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();

        //dtoMono.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return dtoMono;
    }

    /**
     * putPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 수정한다(전체 수정)
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return Mono BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto putPost(Long id, BoardInput input) {

        BoardDto dto = webClient.put()
                .uri("/board/list/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();

        return dto;
    }

    /**
     * patchPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 수정한다(부분 수정)
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return Mono BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto patchPost(Long id, BoardInput input) {
        BoardDto dto = webClient.patch()
                .uri("/board/list/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .bodyToMono(BoardDto.class)
                .block();

        return dto;
    }

    /**
     * deletePost 설명
     *
     <pre>
     * - 외부 게시판 API를 통해 게시물을 삭제한다
     * @param id {@link Long}
     * @return ResponseEntity HttpStatus
     * @throw 
     * @author cyh68
     * @since 2023-02-17
     </pre>
     **/
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        ResponseEntity dto = webClient.delete()
                .uri("/board/list/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();

        return dto;
    }
}
