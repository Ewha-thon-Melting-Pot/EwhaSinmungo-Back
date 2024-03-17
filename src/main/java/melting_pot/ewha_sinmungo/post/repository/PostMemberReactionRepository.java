package melting_pot.ewha_sinmungo.post.repository;

import melting_pot.ewha_sinmungo.post.domain.PostMemberReaction;
import melting_pot.ewha_sinmungo.post.domain.PostMemberReactionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberReactionRepository extends JpaRepository<PostMemberReaction, PostMemberReactionId> {
}
