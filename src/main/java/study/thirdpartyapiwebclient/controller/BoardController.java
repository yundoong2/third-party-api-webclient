package study.thirdpartyapiwebclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.thirdpartyapiwebclient.services.BoardExternalService;
import study.thirdpartyapiwebclient.services.dto.BoardDto;

import java.util.List;

/**
 * ApiController 설명
 *
 * <pre>
 * - 외부 게시판 HTTP API 호출 및 응답받기 위한 Controller
 * @author cyh68
 * @since 2023-02-14
 * </pre>
 **/
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardExternalService service;

    /**
     * getBoardList 설명
     *
     * <pre>
     * - 게시판 전체 게시글 조회 Method
     * @return List BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-14
     * </pre>
     **/
    @GetMapping(value = {"/", "/list", "/board/list"})
    public Flux<BoardDto> getBoardList() {
        return service.GetBoardList();
    }

    /**
     * getPost 설명
     *
     <pre>
     * - 게시판 특정 게시글 조회 Method
     * @param id {@link Long}
     * @return 
     * @throw 
     * @author cyh68
     * @since 2023-02-15
     </pre>
     **/
    @GetMapping(value = "/board/list/{id}")
    public Mono<BoardDto> getPost(@PathVariable Long id) {
        return service.GetBoardById(id);
    }
}
