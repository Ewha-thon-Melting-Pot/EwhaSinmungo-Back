package melting_pot.ewha_sinmungo.post.controller;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.global.apiResponse.ApiResponse;
import melting_pot.ewha_sinmungo.post.dto.requestDto.PostRequestDTO;
import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Status;
import melting_pot.ewha_sinmungo.post.service.PostService;
import melting_pot.ewha_sinmungo.vote.service.VoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final VoteService voteService;

    //게시글 작성
    @PostMapping("/article")
    public String postPost (@RequestBody @Valid PostRequestDTO.PostSaveDto request) {
        postService.createPost(request);
        return "Success";
    }

    //게시글 상세 조회
    @GetMapping("/article/{postId}")
    public ApiResponse<PostResponseDTO.PostEntityDto> getPost (@PathVariable Long postId){
        return ApiResponse.onSuccess(postService.getPostInfo(postId));
    }

    //게시글 분야+진행상황별 목록 조회(최신순)
    @GetMapping("/article/previews/{category}/{status}/newest")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewByCategoryNewest(@PathVariable Category category, @PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewByCategoryNewest(category, status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //게시글 분야+진행상황별 목록 조회(오래된순)
    @GetMapping("/article/previews/{category}/{status}/oldest")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewByCategoryOldest(@PathVariable Category category, @PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewByCategoryOldest(category, status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //게시글 분야+진행상황별 목록 조회(인기순)
    @GetMapping("/article/previews/{category}/{status}/hot")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewByCategoryHot(@PathVariable Category category, @PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewByCategoryNewest(category, status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //게시글 진행상황별 목록 조회(최신순)
    @GetMapping("/article/previews/{status}/newest")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewNewest(@PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewNewest(status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //게시글 진행상황별 목록 조회(오래된순)
    @GetMapping("/article/previews/{status}/oldest")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewOldest( @PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewOldest(status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //게시글 진행상황별 목록 조회(인기순)
    @GetMapping("/article/previews/{status}/hot")
    public ApiResponse<Page<PostResponseDTO.PostPreviewDto>> getPostPreviewHot(@PathVariable Status status, Pageable pageable) {
        Page<PostResponseDTO.PostPreviewDto> previews = postService.getPostPreviewHot(status, pageable);
        return ApiResponse.onSuccess(previews);
    }

    //투표 생성
    @PostMapping("/article/{postId}/vote")
    @ResponseStatus(value = HttpStatus.OK)
    public String createVote(@PathVariable final Long postId){
        voteService.createVote(postId);
        return "투표했습니다.";
    }

    //투표 삭제
    @DeleteMapping("/article/{postId}/vote")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteVote(@PathVariable final Long postId){
        voteService.deleteVote(postId);
        return "투표를 취소했습니다.";
    }

}
