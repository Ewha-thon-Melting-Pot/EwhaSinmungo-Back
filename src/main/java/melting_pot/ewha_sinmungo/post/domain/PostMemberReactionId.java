package melting_pot.ewha_sinmungo.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;

import java.io.Serializable;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostMemberReactionId implements Serializable  {
    private Post post;
    private Member member;
}
