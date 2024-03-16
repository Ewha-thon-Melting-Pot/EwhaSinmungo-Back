package melting_pot.ewha_sinmungo.member.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    STUDENT("STUDENT"),
    GUEST("GUEST"),
    ADMIN("ADMIN");

    private final String key;
}
