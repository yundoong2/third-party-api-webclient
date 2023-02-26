package study.thirdpartyapiwebclient.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import study.thirdpartyapiwebclient.ThirdPartyApiWebclientApplicationTests;
import study.thirdpartyapiwebclient.common.Constants;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
import study.thirdpartyapiwebclient.services.BoardExternalService;
import study.thirdpartyapiwebclient.services.dto.BoardDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ThirdPartyApiWebclientApplicationTests.class)
@ActiveProfiles({"test"})
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ServiceTest {
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
    @DisplayName("게시글 전체 조회 성공 테스트")
    public void getBoardListTest() {
        //Given
        //When
        List<BoardDto> response = boardService.getBoardList();

        //Then
        assertNotNull(response);
    }

    @Test
    @DisplayName("특정 게시글 조회 성공 테스트")
    public void getPostByIdTest() {
        //Given
        Long id = Constants.TEST_ID;

        //When
        BoardDto response = boardService.getPostById(id);

        //Then
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
        assertEquals(response.getTitle(), Constants.TEST_TITLE);
        assertEquals(response.getContent(), Constants.TEST_CONTENT);
        assertEquals(response.getWriter(), Constants.TEST_WRITER);
        assertEquals(response.getHit(), Constants.TEST_HIT);
    }

    @Test
    @DisplayName("게시글 등록 성공 테스트")
    public void addPostTest() {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        BoardDto response = boardService.addPost(input);

        //Then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(response.getTitle(), Constants.TEST_TITLE);
        assertEquals(response.getContent(), Constants.TEST_CONTENT);
        assertEquals(response.getWriter(), Constants.TEST_WRITER);
        assertEquals(response.getHit(), Constants.TEST_HIT);
    }

    @Test
    @DisplayName("게시글 전체 수정 성공 테스트")
    public void updatePostPutTest() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE_EDITED);
        input.setContent(Constants.TEST_CONTENT_EDITED);
        input.setWriter(Constants.TEST_WRITER_EDITED);
        input.setHit(Constants.TEST_HIT_EDITED);

        //When
        BoardDto response = boardService.putPost(id, input);

        //Given
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
        assertEquals(response.getTitle(), Constants.TEST_TITLE_EDITED);
        assertEquals(response.getContent(), Constants.TEST_CONTENT_EDITED);
        assertEquals(response.getWriter(), Constants.TEST_WRITER_EDITED);
        assertEquals(response.getHit(), Constants.TEST_HIT_EDITED);
    }

    @Test
    @DisplayName("게시글 부분 수정 성공 테스트")
    public void patchPostTest() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE_EDITED);
        input.setContent(Constants.TEST_CONTENT_EDITED);

        //When
        BoardDto response = boardService.patchPost(id, input);

        //Then
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
        assertEquals(response.getTitle(), Constants.TEST_TITLE_EDITED);
        assertEquals(response.getContent(), Constants.TEST_CONTENT_EDITED);
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    public void deletePostTest() {
        //Given
        Long id = Constants.TEST_ID;

        //When
        ResponseEntity<HttpStatus> response = boardService.deletePost(id);

        //Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }
}
