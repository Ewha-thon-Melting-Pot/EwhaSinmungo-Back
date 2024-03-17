package melting_pot.ewha_sinmungo.result.dto.requestDto;

import lombok.Getter;

import java.time.LocalDateTime;

public class ResultRequestDto {

    @Getter
    public static class ResultSaveDto {
        LocalDateTime createdDate;
        String content;
    }
}