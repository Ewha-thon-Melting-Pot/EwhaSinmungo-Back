package melting_pot.ewha_sinmungo.member.dto;

import lombok.*;
import melting_pot.ewha_sinmungo.member.entity.Member;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private String studentNum;
    private String college;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .studentNum(member.getStudentNum())
                .studentNum(member.getCollege())
                .build();
    }
}
