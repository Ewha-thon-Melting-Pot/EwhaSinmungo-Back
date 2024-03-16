package melting_pot.ewha_sinmungo.member.entity;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {
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

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


//    @Builder
//    public Member(String name, String studentNum, String password, boolean is_student){
//        this.name = name;
//        this.studentNum = studentNum;
//        this.password = password;
//        this.is_student = is_student;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    //사용자 학번 반환(고유한 값)
    @Override
    public String getUsername() {
        return studentNum;
    }

    //사용자 패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;//만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;//잠금되지 않았음
    }

    //패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;//만료되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;//사용 가능
    }
}
