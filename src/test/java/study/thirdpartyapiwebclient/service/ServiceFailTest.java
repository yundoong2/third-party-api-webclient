package study.thirdpartyapiwebclient.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.thirdpartyapiwebclient.common.Constants;
import study.thirdpartyapiwebclient.config.exception.BadRequestException;
import study.thirdpartyapiwebclient.config.exception.CustomException;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
import study.thirdpartyapiwebclient.services.BoardExternalService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceFailTest {
    @Autowired
    BoardExternalService boardService;

    @BeforeEach
    public void beforeEach() {
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        boardService.addPost(input);
    }

    @Test
    @DisplayName("특정 게시글 조회 실패 테스트")
    public void getPostByIdTest_fail() {
        //Given
        Long id = Constants.TEST_FAIL_ID;

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.getPostById(id);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
        //exception 안에 code와 message는 어떤식으로 넣어야 할까?
//        assertEquals("", e.getCode());
//        assertEquals("", e.getMessage());
    }

    @Test
    @DisplayName("게시글 등록 실패 테스트")
    public void addPostTest_fail() {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.addPost(input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 전체수정 실패 테스트")
    public void updatePostPutTest_fail() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.putPost(id, input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 전체수정 실패 테스트")
    public void updatePostPatchTest_fail() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.patchPost(id, input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 삭제 실패 테스트")
    public void deletePostTest() {
        //Given
        Long id = Constants.TEST_FAIL_ID;

        //When
        Throwable exception = assertThrows(CustomException.class, () ->{
            boardService.deletePost(id);
        });

        //Then
        CustomException e = (CustomException) exception;
    }
}
