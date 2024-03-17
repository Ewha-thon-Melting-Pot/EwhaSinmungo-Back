package melting_pot.ewha_sinmungo.vote.entity;

import javax.persistence.*;

import lombok.Builder;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;

@Entity
@NoArgsConstructor
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    @Builder
    public Vote(Post post, Member member){
        this.post = post;
        this.member = member;
    }
}
