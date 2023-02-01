package IntegrationTestUtils;

import app.foot.controller.rest.Player;
import app.foot.controller.rest.PlayerScorer;

public class AddGoalsInMtachUtils {
    private static Player player1() {
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
    public static PlayerScorer PlayerScorer1(){
        return PlayerScorer.builder()
                .player(player1())
                .scoreTime(45)
                .isOG(false)
                .build();
    }
    public static PlayerScorer PlayerScorer2(){
        return PlayerScorer.builder()
                .player(player2())
                .scoreTime(63)
                .isOG(false)
                .build();
    }
}
