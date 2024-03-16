package melting_pot.ewha_sinmungo.post.converter;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import melting_pot.ewha_sinmungo.post.dto.requestDto.PostRequestDTO;
import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.entity.PostUrl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverter {
    private final MemberService memberService;
    public Post toPostEntity (PostRequestDTO.PostSaveDto request) {
        Member member = memberService.getCurrentMember();

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .member(member)
                .build();
        post.updatePostUrls(request.getUrlList());
        return post;
    }

    public PostResponseDTO.PostEntityDto toPostEntityDto(Post post) {
        Member member = memberService.getCurrentMember();
        LocalDateTime currentDate = post.getCreatedDate();
        LocalDateTime deadline = currentDate.plus(30, ChronoUnit.DAYS);
        List<String> urlList = post.getPostUrlList().stream()
                .map(PostUrl::getUrl)
                .collect(Collectors.toList());
        return PostResponseDTO.PostEntityDto.builder()
                .postId(post.getPostId())
                .studentName(member.getName())
                .studentNum(member.getStudentNum())
                .email(post.getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .urlList(urlList)
                .deadline(deadline)
                .voteCount(post.getVoteCount())
                .status(post.getStatus())
                .createdDate(post.getCreatedDate())
                .build();
    }
}
