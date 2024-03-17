package melting_pot.ewha_sinmungo.result.repository;

import melting_pot.ewha_sinmungo.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
