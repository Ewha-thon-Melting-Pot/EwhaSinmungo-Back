package melting_pot.ewha_sinmungo.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String name;
    private String studentNum;
    private String password;
    private String college;
    private Boolean isStudent;
    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, Boolean is_student, List<String> roles) {

        return Member.builder()
                .name(name)
                .studentNum(studentNum)
                .college(college)
                .password(encodedPassword)
                .is_student(is_student)
                .roles(roles)
                .build();
    }
}