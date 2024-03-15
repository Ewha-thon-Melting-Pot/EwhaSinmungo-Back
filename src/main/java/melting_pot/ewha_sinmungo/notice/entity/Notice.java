package melting_pot.ewha_sinmungo.notice.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import melting_pot.ewha_sinmungo.member.entity.Member;

@Entity
@NoArgsConstructor
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
}
