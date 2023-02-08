package IntegrationTestUtils;

import app.foot.controller.rest.Match;
import app.foot.controller.rest.Team;
import app.foot.controller.rest.TeamMatch;

import java.time.Instant;
import java.util.List;

public class MatchThreeUtils {
    public static Match expectedMatch3() {
        return Match.builder()
                .id(3)
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .stadium("S3")
                .datetime(Instant.parse("2023-01-01T18:00:00Z"))
                .build();
    }
    private static Team team1() {
        return Team.builder()
                .id(1)
                .name("E1")
                .build();
    }
    private static Team team3() {
        return Team.builder()
                .id(3)
                .name("E3")
                .build();
    }

    private static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team1())
                .score(0)
                .scorers(List.of())
                .build();
    }

    private static TeamMatch teamMatchB() {
        return TeamMatch.builder()
                .team(team3())
                .score(0)
                .scorers(List.of())
                .build();
    }
}
