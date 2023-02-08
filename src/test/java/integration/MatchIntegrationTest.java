package integration;

import app.foot.FootApi;
import app.foot.controller.rest.*;
import app.foot.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static IntegrationTestUtils.AddGoalsInMtachUtils.PlayerScorer1;
import static IntegrationTestUtils.AddGoalsInMtachUtils.PlayerScorer2;
import static IntegrationTestUtils.MatchOneUtils.expectedMatch1;
import static IntegrationTestUtils.MatchThreeUtils.expectedMatch3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static IntegrationTestUtils.MatchTwooUtils.expectedMatch2;

@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
class MatchIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    MatchService service = mock(MatchService.class);
    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();  //Allow 'java.time.Instant' mapping


    @Test
    void read_match_by_id_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches/2"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        Match actual = objectMapper.readValue(
                response.getContentAsString(), Match.class);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedMatch2(), actual);
    }

    @Test
    void get_all_matches_OK() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<Match> actual = convertFromHttpResponse(response);
        Match match3 = expectedMatch3();
        Match match2 = expectedMatch2();
        Match match1 = expectedMatch1();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(actual.containsAll(List.of(
                match1,
                match2,
                match3)));
    }

    @Test
    public void addMatchGoalsById_OK() throws Exception {
        int matchId = 3;
        ArrayList<PlayerScorer> scorers = new ArrayList<>();
        scorers.add(PlayerScorer1());
        scorers.add(PlayerScorer2());

        int exceptedGoals = scorers.size();
        ResultActions response = mockMvc.perform(post("/matches/{matchId}/goals", matchId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(scorers)));
        response.andExpect(status().isOk());
        Match match = new ObjectMapper().findAndRegisterModules().readValue(
                response.andReturn().getResponse().getContentAsString(), Match.class);
        match.getTeamA().setScore(exceptedGoals);
        assertEquals(match.getId(), matchId);
        assertEquals(exceptedGoals, match.getTeamA().getScore());
    }

    private List<Match> convertFromHttpResponse(MockHttpServletResponse response)
            throws JsonProcessingException, UnsupportedEncodingException {
        CollectionType matchListType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Match.class);
        return objectMapper.readValue(
                response.getContentAsString(),
                matchListType);
    }
}
