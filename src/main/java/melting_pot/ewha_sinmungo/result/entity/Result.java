package melting_pot.ewha_sinmungo.result.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultId")
    private Long id;

    //내용
    @Column(nullable = false)
    private String content;

    //생성날짜
    @Column(nullable = false)
    private LocalDateTime createdDate;

    //관리자
    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

    //게시글
    @OneToOne
    @JoinColumn(name = "postId",nullable = false)
    private Post post;

    public void updateContent(String content) {this.content = content;}

}
