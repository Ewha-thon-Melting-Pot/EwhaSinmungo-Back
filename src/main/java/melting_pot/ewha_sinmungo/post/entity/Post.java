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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Setter
    @Builder.Default
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int voteCount = 0;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ONGOING;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isHot = false;

    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

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
