package melting_pot.ewha_sinmungo.post.dto.requestDto;

import lombok.Getter;
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

public class PostRequestDTO {

    @Getter
    public static class PostSaveDto {
        String email;
        String title;
        String content;
        Category category;
        List<String> urlList;
    }
}