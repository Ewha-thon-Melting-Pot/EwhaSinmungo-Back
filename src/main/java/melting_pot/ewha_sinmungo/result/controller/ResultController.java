package melting_pot.ewha_sinmungo.result.controller;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.result.dto.requestDto.ResultRequestDto;
import melting_pot.ewha_sinmungo.result.service.ResultService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/admin/{postId}/result")
    public String postResult (@RequestBody @Valid ResultRequestDto.ResultSaveDto request, @PathVariable Long postId) {
        resultService.createResult(request, postId);
        return "Success";
    }

    @PutMapping("/admin/{postId}/result")
    public String updateResult (@RequestBody @Valid ResultRequestDto.ResultSaveDto request, @PathVariable Long postId) {
        resultService.updateResult(request, postId);
        return "Success";
    }
}
