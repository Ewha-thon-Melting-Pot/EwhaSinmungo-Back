package melting_pot.ewha_sinmungo.result.converter;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.service.PostService;
import melting_pot.ewha_sinmungo.result.dto.requestDto.ResultRequestDto;
import melting_pot.ewha_sinmungo.result.entity.Result;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultConverter {
    private final MemberService memberService;
    private final PostService postService;
    public Result toResultEntity (ResultRequestDto.ResultSaveDto request, Long postId) {
        Member member = memberService.getCurrentMember();
        Post post = postService.findById(postId);
        Result result = Result.builder()
                .content(request.getContent())
                .createdDate(request.getCreatedDate())
                .member(member)
                .post(post)
                .build();
        return result;
    }
}

