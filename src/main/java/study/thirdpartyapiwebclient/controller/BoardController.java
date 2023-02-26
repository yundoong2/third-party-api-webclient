package study.thirdpartyapiwebclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;
import study.thirdpartyapiwebclient.config.exception.CustomException;
import study.thirdpartyapiwebclient.controller.dto.BaseResponse;
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
     * @return List BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-14
     * </pre>
     **/
    @GetMapping(value = {"/", "/externBoard/list"})
    public BaseResponse getBoardList() {
        List<BoardDto> result = service.getBoardList();

        return new BaseResponse().success(result);
    }

    /**
     * getPost 설명
     *
     * <pre>
     * - 게시판 특정 게시글 조회 Method
     * @param id {@link Long}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-15
     * </pre>
     **/
    @GetMapping(value = "/externBoard/list/{id}")
    public BaseResponse getPost(@PathVariable Long id) {
        if (id == 0)
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_ID_ERROR);

        BoardDto result = service.getPostById(id);

        return new BaseResponse().success(result);
    }

    /**
     * addPost 설명
     *
     * <pre>
     * - 게시글 등록 Method
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    @PostMapping(value = "/externBoard/list")
    public BaseResponse addPost(@RequestBody BoardInput input) {
        if (input == null ||
                input.getTitle() == null ||
                input.getContent() == null ||
                input.getWriter() == null) {
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_BODY_ERROR);
        }

        BoardDto result = service.addPost(input);

        return new BaseResponse().success(result);
    }

    /**
     * putPost 설명
     *
     * <pre>
     * - 게시글 내용 전체 수정 Method
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-16
     * </pre>
     **/
    @PutMapping(value = "/externBoard/list/{id}")
    public BaseResponse putPost(@PathVariable Long id, @RequestBody BoardInput input) {
        if (id == 0)
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_ID_ERROR);

        if (input == null ||
                input.getId() == null ||
                input.getTitle() == null ||
                input.getContent() == null ||
                input.getWriter() == null ||
                input.getHit() == null) {
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_BODY_ERROR);
        }

        BoardDto result = service.putPost(id, input);

        return new BaseResponse().success(result);
    }

    /**
     * patchPost 설명
     *
     * <pre>
     * - 게시글 내용 부분 수정 Method
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-17
     * </pre>
     **/
    @PatchMapping(value = "/externBoard/list/{id}")
    public BaseResponse patchPost(@PathVariable Long id, @RequestBody BoardInput input) {
        if (id == 0)
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_ID_ERROR);

        if (input == null || input.getId() == null) {
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_BODY_ERROR);
        }

        BoardDto result = service.patchPost(id, input);

        return new BaseResponse().success(result);
    }

    /**
     * deletePPost 설명
     *
     * <pre>
     * - 게시글 삭제 Method
     * @param id {@link Long}
     * @return ResponseEntity {@link org.springframework.http.ResponseEntity}
     * @throw CustomException {@link CustomException}
     * @author cyh68
     * @since 2023-02-19
     * </pre>
     **/
    public BaseResponse deletePPost(@PathVariable Long id) {
        if (id == 0)
            throw new CustomException(CustomErrorCode.INVALID_REQUEST_ID_ERROR);

        ResponseEntity<HttpStatus> result = service.deletePost(id);

        return new BaseResponse().success(result);
    }
}
