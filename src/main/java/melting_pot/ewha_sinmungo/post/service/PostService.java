package melting_pot.ewha_sinmungo.post.service;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.global.apiResponse.exception.GeneralException;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import melting_pot.ewha_sinmungo.post.converter.PostConverter;
import melting_pot.ewha_sinmungo.post.dto.requestDto.PostRequestDTO;
import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.entity.Status;
import melting_pot.ewha_sinmungo.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static melting_pot.ewha_sinmungo.global.apiResponse.code.status.ErrorStatus.ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    //게시글 작성
    @Transactional
    public void createPost(PostRequestDTO.PostSaveDto request){
        Post post = postConverter.toPostEntity(request);
        postRepository.save(post);
    }

    //게시글 상세 조회
    public PostResponseDTO.PostEntityDto getPostInfo(Long postId) {
        Post post = findById(postId);
        return postConverter.toPostEntityDto(post);
    }

    //게시글 분야+진행상황별 목록 조회(최신순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryNewest(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByCreatedDateDesc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    //게시글 분야+진행상황별 목록 조회(오래된순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryOldest(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByCreatedDateAsc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    //게시글 분야+진행상황별 목록 조회(인기순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewByCategoryHot(Category category, Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByCategoryAndStatusOrderByVoteCountDesc(category, status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    //게시글 진행상황별 목록 조회(최신순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewNewest(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByCreatedDateDesc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    //게시글 진행상황별 목록 조회(오래된순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewOldest(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByCreatedDateAsc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    //게시글 진행상황별 목록 조회(인기순)
    public Page<PostResponseDTO.PostPreviewDto> getPostPreviewHot(Status status, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByStatusOrderByVoteCountDesc(status, pageable);
        return postConverter.toPreviewListDto(posts);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new GeneralException(ARTICLE_NOT_FOUND));
    }
}
