package IntegrationTestUtils;

import app.foot.controller.rest.*;

import java.time.Instant;
import java.util.List;

public class MatchOneUtils {
    public static Match expectedMatch1() {
        return Match.builder()
                .id(1)
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .stadium("S1")
                .datetime(Instant.parse("2023-01-01T10:00:00Z"))
                .build();
    }
    public static Player player1() {
        return Player.builder()
                .id(1)
                .name("J1")
                .isGuardian(false)
                .build();
    }
    private static Player player2() {
        return Player.builder()
                .id(2)
                .name("J2")
                .isGuardian(false)
                .build();
    }
    private static Player player3() {
        return Player.builder()
                .id(3)
                .name("J3")
                .isGuardian(false)
                .build();
    }
    private static Player player4() {
        return Player.builder()
                .id(4)
                .name("J4")
                .isGuardian(false)
                .build();
    }

    private static Team team1() {
        return Team.builder()
                .id(1)
                .name("E1")
                .build();
    }

    private static Team team2() {
        return Team.builder()
                .id(2)
                .name("E2")
                .build();
    }

    private static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team1())
                .score(4)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(30)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(20)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(10)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player4())
                                .scoreTime(60)
                                .isOG(true)
                                .build()))
                .build();
    }
    private static TeamMatch teamMatchB() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player2())
                                .scoreTime(40)
                                .isOG(true)
                                .build(),
                        PlayerScorer.builder()
                                .player(player3())
                                .scoreTime(50)
                                .isOG(false)
                                .build()))
                .build();
    }
}
