package melting_pot.ewha_sinmungo.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String studentNum;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean is_student;
}
