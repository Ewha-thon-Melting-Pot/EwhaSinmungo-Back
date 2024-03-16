package melting_pot.ewha_sinmungo.post.repository;

import melting_pot.ewha_sinmungo.post.dto.responseDto.PostResponseDTO;
import melting_pot.ewha_sinmungo.post.entity.Category;
import melting_pot.ewha_sinmungo.post.entity.Post;
import melting_pot.ewha_sinmungo.post.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategoryAndStatusOrderByCreatedDateDesc(Category category, Status status, Pageable pageable);
    Page<Post> findAllByCategoryAndStatusOrderByCreatedDateAsc(Category category, Status status, Pageable pageable);
    Page<Post> findAllByCategoryAndStatusOrderByVoteCountDesc(Category category, Status status, Pageable pageable);

    Page<Post> findAllByStatusOrderByCreatedDateDesc(Status status, Pageable pageable);
    Page<Post> findAllByStatusOrderByCreatedDateAsc(Status status, Pageable pageable);
    Page<Post> findAllByStatusOrderByVoteCountDesc(Status status, Pageable pageable);

}

