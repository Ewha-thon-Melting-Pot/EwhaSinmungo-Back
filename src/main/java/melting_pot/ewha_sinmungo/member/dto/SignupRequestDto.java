package melting_pot.ewha_sinmungo.member.dto;


import lombok.*;
import melting_pot.ewha_sinmungo.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDto {
    private String name;
    private String studentNum;
    private String college;
    private String password;
}
