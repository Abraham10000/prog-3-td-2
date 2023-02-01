package unit;
import app.foot.controller.rest.Player;
import app.foot.repository.TeamRepository;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.MatchMapper;
import app.foot.repository.mapper.PlayerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import app.foot.repository.PlayerRepository;

import static IntegrationTestUtils.MatchOneUtils.player1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class PlayerEntityTest {
    private TeamRepository teamRepository = mock(TeamRepository.class);
    private PlayerMapper playerMapper = mock(PlayerMapper.class);

    @Test
    public void toEntityTest() {
        Player player = player1();
        TeamEntity team = TeamEntity.builder().id(1).name("E1").build();
        PlayerEntity expectedPlayerEntity = PlayerEntity.builder()
                .id(1)
                .name("J1")
                .team(team)
                .guardian(false)
                .build();

        when(teamRepository.findByName("E1")).thenReturn(team);

        app.foot.model.Player playerToEntity = app.foot.model.Player.builder()
                .id(1)
                .isGuardian(false)
                .name("J1")
                .teamName("E1").build();

        PlayerEntity actualPlayerEntity = playerMapper.toEntity(playerToEntity);

        assertEquals(expectedPlayerEntity, actualPlayerEntity);
    }

}
