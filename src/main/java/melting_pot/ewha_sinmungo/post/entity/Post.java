package melting_pot.ewha_sinmungo.post.entity;

import javax.persistence.*;

import lombok.*;
import melting_pot.ewha_sinmungo.global.BaseTimeEntity;
import melting_pot.ewha_sinmungo.global.apiResponse.exception.GeneralException;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.notice.entity.Notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long postId;

    //제목
    @Column(nullable = false)
    private String title;

    //내용
    @Column(nullable = false)
    private String content;

    //목록
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    //투표수
    @Setter
    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int voteCount = 0;

    //마감기한
    @Column(nullable = false)
    private LocalDateTime deadline;

    //진행상황
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ONGOING;

    //이메일
    @Column(nullable = false)
    private String email;

    //생성날짜
    @Column(nullable = false)
    private LocalDateTime createdDate;

    //인기게시물인지
    @Builder.Default
    @Column(nullable = false)
    private Boolean isHot = false;

    //작성자
    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

    //첨부파일
    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostUrl> postUrlList = new ArrayList<>();


    public void increaseVoteCount() {
        this.voteCount += 1;
        if(voteCount >= 90){
            isHot = Boolean.TRUE;
        }
    }
    public void decreaseVoteCount() {
        this.voteCount -= 1;
    }

    public void updatePostUrls(List<String> postUrls){
        Set<String> uniqueUrls = new HashSet<>(postUrls);
        postUrlList = uniqueUrls.stream()
                .map(postUrl -> PostUrl.builder()
                        .url(postUrl)
                        .post(this)
                        .build())
                .collect(Collectors.toList());
    }


}
