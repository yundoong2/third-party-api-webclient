package study.thirdpartyapiwebclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
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
    @GetMapping(value = {"/", "/externBoard/list"})
    public List<BoardDto> getBoardList() {
        return service.GetBoardList();
    }

    /**
     * getPost 설명
     *
     * <pre>
     * - 게시판 특정 게시글 조회 Method
     * @param id {@link Long}
     * @return
     * @throw
     * @author cyh68
     * @since 2023-02-15
     * </pre>
     **/
    @GetMapping(value = "/externBoard/list/{id}")
    public BoardDto getPost(@PathVariable Long id) {
        return service.GetPostById(id);
    }

    /**
     * addPost 설명
     *
     * <pre>
     * - 게시글 등록 Method
     * @param input {@link BoardInput}
     * @return Mono BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    @PostMapping(value = "/externBoard/list")
    public BoardDto addPost(@RequestBody BoardInput input) {
        return service.AddPost(input);
    }

    /**
     * putPost 설명
     *
     * <pre>
     * - 게시글 내용 전체 수정 Method
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto
     * @throw
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    @PutMapping(value = "/externBoard/list/{id}")
    public BoardDto putPost(@PathVariable Long id, @RequestBody BoardInput input) {
        return service.putPost(id, input);
    }

    /**
     * patchPost 설명
     *
     <pre>
     * - 게시글 내용 부분 수정 Method
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto
     * @throw 
     * @author cyh68
     * @since 2023-02-17
     </pre>
     **/
    @PatchMapping(value = "/externBoard/list/{id}")
    public BoardDto patchPost(@PathVariable Long id, @RequestBody BoardInput input) {
        return service.patchPost(id, input);
    }
}
