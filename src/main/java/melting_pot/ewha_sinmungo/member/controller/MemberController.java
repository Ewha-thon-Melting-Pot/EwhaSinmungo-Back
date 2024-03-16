package melting_pot.ewha_sinmungo.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import melting_pot.ewha_sinmungo.global.config.SecurityUtil;
import melting_pot.ewha_sinmungo.global.jwt.JwtToken;
import melting_pot.ewha_sinmungo.member.dto.LoginRequestDto;
import melting_pot.ewha_sinmungo.member.dto.MemberDto;
import melting_pot.ewha_sinmungo.member.dto.SignUpDto;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public JwtToken logIn(@RequestBody LoginRequestDto requestDto) {
        String studentNum = requestDto.getStudentNum();
        String password = requestDto.getPassword();
        JwtToken jwtToken = memberService.logIn(studentNum, password);
        log.info("request studentNum = {}, password = {}", studentNum, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto){
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(savedMemberDto);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
