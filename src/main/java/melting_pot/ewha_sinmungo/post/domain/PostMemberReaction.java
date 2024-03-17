package melting_pot.ewha_sinmungo.post.domain;

import lombok.*;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;


import javax.persistence.*;

@Entity
@Builder
@Getter
@IdClass(PostMemberReactionId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostMemberReaction {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private boolean isLiked = false;

    public void enableLike() {
        if(!isLiked){
            post.increaseVoteCount();
        }
        this.isLiked = true;
    }
    public void disableLike(){
        if(isLiked){
            post.decreaseVoteCount();
        }
        this.isLiked = false;
    }
}
