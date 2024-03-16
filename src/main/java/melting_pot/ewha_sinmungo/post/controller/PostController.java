package melting_pot.ewha_sinmungo.post.controller;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.global.apiResponse.ApiResponse;
import melting_pot.ewha_sinmungo.post.dto.requestDto.PostRequestDTO;
import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/article")
    public String postPost (@RequestBody @Valid PostRequestDTO.PostSaveDto request) {
        postService.createPost(request);
        return "Success";
    }

    @GetMapping("/article/{postId}")
    public ApiResponse<PostResponseDTO.PostEntityDto> getPost (@PathVariable Long postId){
        return ApiResponse.onSuccess(postService.getPostInfo(postId));
    }

}
