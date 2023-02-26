package study.thirdpartyapiwebclient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;
import study.thirdpartyapiwebclient.config.exception.CustomException;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
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
     * - 외부 게시판 API를 통해 전체 게시글을 조회한다
     * @return LIst BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-14
     * </pre>
     **/
    public List<BoardDto> getBoardList() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list")
                .build(true);

        List<BoardDto> result = webClient.get()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToFlux(BoardDto.class)
                .collectList()
                .block();

        //result.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return result;
    }

    /**
     * GetBoardById 설명
     *
     * <pre>
     * - - 외부 게시판 API를 통해 특정 게시글을 조회한다
     * @param id {@link Long}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-15
     * </pre>
     **/
    public BoardDto getPostById(Long id) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        BoardDto result = webClient.get()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToMono(BoardDto.class)
                .block();

        //result.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return result;
    }

    /**
     * AddPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 등록한다
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto addPost(BoardInput input) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list")
                .build(false);

        BoardDto result = webClient.post()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToMono(BoardDto.class)
                .block();

        //result.subscribe(boardDto -> log.info(String.valueOf(boardDto)));

        return result;
    }

    /**
     * putPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 수정한다(전체 수정)
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto putPost(Long id, BoardInput input) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        BoardDto result = webClient.put()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToMono(BoardDto.class)
                .block();

        return result;
    }

    /**
     * patchPost 설명
     *
     * <pre>
     * - 외부 게시판 API를 통해 게시글을 수정한다(부분 수정)
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    public BoardDto patchPost(Long id, BoardInput input) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        BoardDto result = webClient.patch()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(input), BoardInput.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToMono(BoardDto.class)
                .block();

        return result;
    }

    /**
     * deletePost 설명
     *
     <pre>
     * - 외부 게시판 API를 통해 게시물을 삭제한다
     * @param id {@link Long}
     * @return ResponseEntity HttpStatus {@link ResponseEntity}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-17
     </pre>
     **/
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + id)
                .build(false);

        ResponseEntity result = webClient.delete()
                .uri(uriComponents.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.BAD_REQUEST_ERROR))));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.createException()
                            .flatMap(it -> Mono.error((new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))));
                })
                .bodyToMono(ResponseEntity.class)
                .block();

        return result;
    }
}
