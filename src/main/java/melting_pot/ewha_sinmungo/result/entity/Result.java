package melting_pot.ewha_sinmungo.result.entity;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.admin.entity.Admin;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.post.entity.Post;

@Entity
@NoArgsConstructor
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false)
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
