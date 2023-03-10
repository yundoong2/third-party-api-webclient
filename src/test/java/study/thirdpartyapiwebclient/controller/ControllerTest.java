package study.thirdpartyapiwebclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import study.thirdpartyapiwebclient.ThirdPartyApiWebclientApplicationTests;
import study.thirdpartyapiwebclient.common.Constants;
import study.thirdpartyapiwebclient.controller.dto.BoardInput;
import study.thirdpartyapiwebclient.services.BoardExternalService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ThirdPartyApiWebclientApplicationTests.class)
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ControllerTest {
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
    @DisplayName("?????? ????????? ?????? ?????? ?????????")
    public void getBoardListTest() throws Exception {
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get("/externBoard/list")
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    @DisplayName("?????? ????????? ?????? ?????? ?????????")
    public void getPostTest() throws Exception {
        //Given
        Long id = Constants.TEST_ID;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/externBoard/list/" + id)
                .build(false);

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get(uriComponents.toString())
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value(Constants.TEST_TITLE));
        resultActions.andExpect(jsonPath("$.data.content").value(Constants.TEST_CONTENT));
        resultActions.andExpect(jsonPath("$.data.writer").value(Constants.TEST_WRITER));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void addPostTest() throws Exception {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("????????? ??????2");
        input.setContent("????????? ??????2");
        input.setWriter("????????? ?????????2");

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
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ??????2"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ??????2"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ?????????2"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ???????????? ?????? ?????????")
    public void putPostTest() throws Exception {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("????????? ?????? - ??????");
        input.setContent("????????? ?????? - ??????");
        input.setWriter("????????? ????????? - ??????");
        input.setHit(1L);

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/externBoard/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .put(uriComponents.toString())
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ?????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ?????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ????????? - ??????"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(1));
    }

    @Test
    @DisplayName("????????? ???????????? ?????? ?????????")
    public void patchPostTest() throws Exception {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("????????? ?????? - ?????? ??????");
        input.setContent("????????? ?????? - ?????? ??????");

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/externBoard/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .patch(uriComponents.toString())
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input));

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.id").value(id));
        resultActions.andExpect(jsonPath("$.data.title").value("????????? ?????? - ?????? ??????"));
        resultActions.andExpect(jsonPath("$.data.content").value("????????? ?????? - ?????? ??????"));
        resultActions.andExpect(jsonPath("$.data.writer").value("????????? ?????????"));
        resultActions.andExpect(jsonPath("$.data.regDate").isNotEmpty());
        resultActions.andExpect(jsonPath("$.data.hit").value(0));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void deletePostTest() throws Exception {
        //Given
        Long id = Constants.TEST_ID;

        UriComponents uriComponents = UriComponentsBuilder
                .fromPath("/externBoard/list/" + id)
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application-json");

        //When
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .delete(uriComponents.toString())
                .headers(headers);

        //Then
        resultActions = mockMvc.perform(builder1);
        resultActions.andDo(print());
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.status").value("OK"));
        resultActions.andExpect(jsonPath("$.message").value("Success"));
        resultActions.andExpect(jsonPath("$.data.statusCode").value("OK"));
        resultActions.andExpect(jsonPath("$.data.statusCodeValue").value(200));
    }

}
