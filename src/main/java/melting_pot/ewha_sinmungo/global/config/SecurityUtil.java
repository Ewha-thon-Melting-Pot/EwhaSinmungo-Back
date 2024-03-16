package melting_pot.ewha_sinmungo.global.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentUserStudentNum(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() ==null){
            throw new RuntimeException("NO authentication information");
        }
        return authentication.getName();
    }
}
