package melting_pot.ewha_sinmungo.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    ONGOING("ONGOING", "진행중"),
    DISCCUSION("DISCCUSION", "논의중"),
    COMPLETION("COMPLETION", "완료");

    private final String label;
    private final String detail;

}
