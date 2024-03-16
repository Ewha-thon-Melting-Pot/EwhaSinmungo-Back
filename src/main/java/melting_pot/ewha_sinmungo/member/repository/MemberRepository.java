package melting_pot.ewha_sinmungo.member.repository;

import melting_pot.ewha_sinmungo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByStudentNum(String studentNum);

    boolean existsByStudentNum(String studentNum);
}
