package melting_pot.ewha_sinmungo.member.dto;


import lombok.*;

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
