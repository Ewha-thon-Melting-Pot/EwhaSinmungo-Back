package melting_pot.ewha_sinmungo.post.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Status;
import java.time.LocalDateTime;
import java.util.List;


public class PostResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostEntityDto {
        Long postId;
        String studentName;
        String studentNum;
        String title;
        String content;
        Category category;
        LocalDateTime deadline;
        String email;
        int voteCount;
        Status status;
        List<String> urlList;
        LocalDateTime createdDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewDto {
        Long postId;
        String title;
        Category category;
        LocalDateTime deadline;
        LocalDateTime createdDate;
        int voteCount;
        long dDay;
    }

}
