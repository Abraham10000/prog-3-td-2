package unit;
import app.foot.controller.rest.Player;
import app.foot.repository.MatchRepository;
import app.foot.repository.TeamRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import app.foot.repository.PlayerRepository;

import java.util.Optional;

import static IntegrationTestUtils.MatchOneUtils.player1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class PlayerEntityTest {
    private TeamRepository teamRepository = mock(TeamRepository.class);
    private MatchRepository matchRepository = mock(MatchRepository.class);

    private PlayerRepository playerRepository = mock(PlayerRepository.class);
    private PlayerMapper playerMapper = new PlayerMapper(matchRepository , playerRepository , teamRepository);

    @Test
    public void toEntityTest() {
        Player player = player1();

        TeamEntity team = TeamEntity.builder()
                .id(1)
                .name("E1")
                .build();
        PlayerEntity expectedPlayerEntity = PlayerEntity.builder()
                .id(1)
                .name("J1")
                .team(TeamEntity.builder()
                        .id(team.getId())
                        .name(team.getName())
                        .build())
                .guardian(false)
                .build();
        when(teamRepository.findByName("E1")).thenReturn(Optional.of(team).get());
        PlayerEntity actualPlayerEntity = playerMapper.toEntity(app.foot.model.Player.builder()
                .id(1)
                .isGuardian(false)
                .name("J1")
                .teamName("E1")
                .build());


        assertEquals(expectedPlayerEntity, actualPlayerEntity);
    }

}