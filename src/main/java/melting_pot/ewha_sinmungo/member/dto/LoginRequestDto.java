package melting_pot.ewha_sinmungo.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String studentNum;
    private String password;
}
