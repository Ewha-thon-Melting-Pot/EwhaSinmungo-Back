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
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.entity.Status;
import melting_pot.ewha_sinmungo.post.repository.PostMemberReactionRepository;
import melting_pot.ewha_sinmungo.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryNewest(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByCreatedDateDesc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryOldest(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByCreatedDateAsc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryHot(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByVoteCountDesc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewNewest(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByCreatedDateDesc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewOldest(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByCreatedDateAsc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewHot(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByVoteCountDesc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }


    @Transactional
    public void enablePostLike(Long postId) {
        Post post = findById(postId);
        Member member = memberService.getCurrentMember();
        PostMemberReaction reaction = getPostMemberReaction(post, member);
        reaction.enableLike();
    }

    @Transactional
    public void disablePostLike(Long postId) {
        Post post = findById(postId);
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
        Post post = postRepository.findById(postId).orElseThrow(()->new GeneralException(ErrorStatus.ARTICLE_NOT_FOUND));
        return post;
    }
}
