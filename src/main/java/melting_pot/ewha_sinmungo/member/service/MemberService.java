package melting_pot.ewha_sinmungo.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import melting_pot.ewha_sinmungo.global.jwt.JwtToken;
import melting_pot.ewha_sinmungo.global.jwt.JwtTokenProvider;
import melting_pot.ewha_sinmungo.member.dto.MemberDto;
import melting_pot.ewha_sinmungo.member.dto.SignUpDto;
import melting_pot.ewha_sinmungo.member.dto.SignupRequestDto;
import melting_pot.ewha_sinmungo.member.entity.Role;
import melting_pot.ewha_sinmungo.member.repository.MemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService{
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtToken logIn(String studentNum, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentNum, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Transactional
    public MemberDto signUp(SignUpDto signUpDto){
        if(memberRepository.existsByStudentNum(signUpDto.getStudentNum())){
            throw new IllegalArgumentException("이미 사용중인 학번입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        Boolean isStudent = false;
        List<String> roles = new ArrayList<>();
        roles.add("GUEST");
        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, isStudent, roles)));
    }

}