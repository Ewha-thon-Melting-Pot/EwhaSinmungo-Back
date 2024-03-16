package melting_pot.ewha_sinmungo.post.service;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.global.apiResponse.code.status.ErrorStatus;
import melting_pot.ewha_sinmungo.global.apiResponse.exception.GeneralException;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import melting_pot.ewha_sinmungo.post.converter.PostConverter;
import melting_pot.ewha_sinmungo.post.domain.PostMemberReaction;
import melting_pot.ewha_sinmungo.post.domain.PostMemberReactionId;
import melting_pot.ewha_sinmungo.post.dto.requestDto.PostRequestDTO;
import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.repository.PostMemberReactionRepository;
import melting_pot.ewha_sinmungo.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;
    private final MemberService memberService;
    private final PostMemberReactionRepository postMemberReactionRepository;

    @Transactional
    public void createPost(PostRequestDTO.PostSaveDto request){
        Post post = postConverter.toPostEntity(request);
        postRepository.save(post);
    }

    public PostResponseDTO.PostEntityDto getPostInfo(Long postId) {
        Post post = findById(postId);
        return postConverter.toPostEntityDto(post);
    }

    @Transactional
    public void enableReviewLike(Long postId) {
        Post post = findById(postId);
        Member member = memberService.getCurrentMember();
        PostMemberReaction reaction = getPostMemberReaction(post, member);
        reaction.enableLike();
    }

    @Transactional
    public void disableReviewLike(Long reviewId) {
        Post post = findById(reviewId);
        Member member = memberService.getCurrentMember();
        PostMemberReaction reaction = getPostMemberReaction(post, member);
        reaction.disableLike();
    }

    private PostMemberReaction getPostMemberReaction(Post post, Member member) {

        PostMemberReactionId reactionId = new PostMemberReactionId(post, member);
        Optional<PostMemberReaction> reactionOptional = postMemberReactionRepository.findById(reactionId);
        if (reactionOptional.isPresent()) {
            return reactionOptional.get();
        } else {
            PostMemberReaction reaction = PostMemberReaction.builder()
                    .post(reactionId.getPost())
                    .member(reactionId.getMember())
                    .build();
            return postMemberReactionRepository.save(reaction);
        }
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new GeneralException(ErrorStatus.ARTICLE_NOT_FOUND));
    }
}
