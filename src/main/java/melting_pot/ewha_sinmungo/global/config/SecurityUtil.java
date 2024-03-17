package melting_pot.ewha_sinmungo.global.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    //현재 로그인한 사용자 학번 가져오기
    public static String getCurrentUserStudentNum(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() ==null){
            throw new RuntimeException("NO authentication information");
        }
        return authentication.getName();
    }
}
