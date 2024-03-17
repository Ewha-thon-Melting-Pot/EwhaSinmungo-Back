package melting_pot.ewha_sinmungo.result.service;

import lombok.RequiredArgsConstructor;
import melting_pot.ewha_sinmungo.global.apiResponse.code.status.ErrorStatus;
import melting_pot.ewha_sinmungo.global.apiResponse.exception.GeneralException;
import melting_pot.ewha_sinmungo.result.converter.ResultConverter;
import melting_pot.ewha_sinmungo.result.dto.requestDto.ResultRequestDto;
import melting_pot.ewha_sinmungo.result.entity.Result;
import melting_pot.ewha_sinmungo.result.repository.ResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResultService {
    private final ResultConverter resultConverter;
    private final ResultRepository resultRepository;

    //논의결과 작성
    @Transactional
    public Result createResult(ResultRequestDto.ResultSaveDto request, Long postId) {
        Result result = resultConverter.toResultEntity(request, postId);
        resultRepository.save(result);
        return result;
    }

    //논의결과 수정
    @Transactional
    public Result updateResult(ResultRequestDto.ResultSaveDto request, Long postId) {
        Result result = findById(postId);
        result.updateContent(request.getContent());
        return result;
    }

    public Result findById(Long postId) {
        return resultRepository.findById(postId).orElseThrow(() -> new GeneralException(ErrorStatus.RESULT_NOT_FOUND));
    }


}