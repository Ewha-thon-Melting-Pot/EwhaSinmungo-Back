package melting_pot.ewha_sinmungo.vote.reporitory;

import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByMemberAndPost(Member member, Post post);

    Optional<Vote> findByMemberAndPost(Member member, Post post);
}
