package melting_pot.ewha_sinmungo.post.repository;

import melting_pot.ewha_sinmungo.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

