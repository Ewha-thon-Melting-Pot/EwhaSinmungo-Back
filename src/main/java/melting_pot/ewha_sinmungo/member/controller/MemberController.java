package melting_pot.ewha_sinmungo.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import melting_pot.ewha_sinmungo.global.jwt.JwtToken;
import melting_pot.ewha_sinmungo.member.dto.LoginRequestDto;
import melting_pot.ewha_sinmungo.member.dto.LoginResponseDto;
import melting_pot.ewha_sinmungo.member.dto.SignUpResponseDto;
import melting_pot.ewha_sinmungo.member.dto.SignupRequestDto;
import melting_pot.ewha_sinmungo.member.entity.Member;
import melting_pot.ewha_sinmungo.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public LoginResponseDto logIn(@RequestBody LoginRequestDto requestDto) {
        String studentNum = requestDto.getStudentNum();
        String password = requestDto.getPassword();
        JwtToken jwtToken = memberService.logIn(studentNum, password);
        log.info("request studentNum = {}, password = {}", studentNum, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return new LoginResponseDto(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignupRequestDto requestDto){
        Member member = memberService.signUp(requestDto);

        return new SignUpResponseDto(member.getName(), member.getStudentNum(), member.getCollege(), member.getRoles());
    }

    @PostMapping("/test")
    public Member test() {
        return memberService.getCurrentMember();
    }

    @GetMapping("/test")
    public String test2(){
        return "테스트 확인";
    }
}
