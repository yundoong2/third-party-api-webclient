package study.thirdpartyapiwebclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import study.thirdpartyapiwebclient.ThirdPartyApiWebclientApplicationTests;
import study.thirdpartyapiwebclient.common.Constants;
import study.thirdpartyapiwebclient.common.constants.CustomErrorCode;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
import study.thirdpartyapiwebclient.services.BoardExternalService;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ThirdPartyApiWebclientApplicationTests.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ControllerFailTest {
    ResultActions resultActions;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardExternalService service;

    @BeforeEach
    public void beforeEach() {
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        service.addPost(input);
    }

    @Test
    @DisplayName("특정 게시글 조회 실패 테스트")
    public void getPostTest_fail() throws Exception {
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get("/externBoard/list/" + Constants.TEST_FAIL_ID)
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.status").value("BAD_REQUEST"));
        resultActions.andExpect(jsonPath("$.message").value(CustomErrorCode.BAD_REQUEST_ERROR.getMessage()));
    }

    @Test
    @DisplayName("게시글 추가 실패 테스트")
    public void addPostTest_fail() throws Exception {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent("테스트 본문2");
        input.setWriter("테스트 작성자2");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .post("/externBoard/list")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.data", nullValue()));
        resultActions.andExpect(jsonPath("$.status").value("BAD_REQUEST"));
        resultActions.andExpect(jsonPath("$.message").value(CustomErrorCode.BAD_REQUEST_ERROR.getMessage()));
    }

    @Test
    @DisplayName("게시글 전체수정 실패 테스트")
    public void putPostTest_fail() throws Exception{
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent("테스트 본문2");
        input.setWriter("테스트 작성자2");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .put("/externBoard/list/" + Constants.TEST_FAIL_ID)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.data", nullValue()));
        resultActions.andExpect(jsonPath("$.status").value("BAD_REQUEST"));
        resultActions.andExpect(jsonPath("$.message").value(CustomErrorCode.BAD_REQUEST_ERROR.getMessage()));
    }

    @Test
    @DisplayName("게시글 부분수정 실패 테스트")
    public void patchPostTest_fail() throws Exception{
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent("테스트 본문2");
        input.setWriter("테스트 작성자2");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .patch("/externBoard/list/" + Constants.TEST_FAIL_ID)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.data", nullValue()));
        resultActions.andExpect(jsonPath("$.status").value("BAD_REQUEST"));
        resultActions.andExpect(jsonPath("$.message").value(CustomErrorCode.BAD_REQUEST_ERROR.getMessage()));
    }

    @Test
    @DisplayName("게시글 삭제 실패 테스트")
    public void deletePostTest_fail() throws Exception{
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/board/list/" + Constants.TEST_FAIL_ID)
                .build(false);

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .delete(uriComponents.toString())
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.status").value("BAD_REQUEST"));
        resultActions.andExpect(jsonPath("$.message").value(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
