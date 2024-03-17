package melting_pot.ewha_sinmungo.post.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postUrlId;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;
}
