package melting_pot.ewha_sinmungo.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    STUDENT_ACTIVITY("STUDENT_ACTIVITY", "학생활동"),
    STUDENT_SUPPORT("STUDENT_SUPPORT", "학생지원"),
    LIVING_SUPPORT("LIVING_SUPPORT", "생활지원"),
    COLLEGE("COLLEGE", "대학");

    private final String label;
    private final String detail;

}
