package app.foot.controller.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Player {
    private Integer id;
    private String name;
    @JsonIgnore
    private String teamName;
    private Boolean isGuardian;
}
