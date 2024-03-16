package melting_pot.ewha_sinmungo.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpResponseDto {

    private String name;
    private String studentNum;
    private String college;
    private List<String> roles = new ArrayList<>();

    @Builder
    public SignUpResponseDto(Member member){
        this.name = member.getName();
        this.studentNum = member.getStudentNum();;
        this.college = member.getCollege();;
        this.roles = member.getRoles();
    }

}