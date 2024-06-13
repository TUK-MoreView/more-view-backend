package com.example.moreveiw.domain.member.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class MemberEditor {

    private final String memberName;
    private final String password;
    private final String role;

    public static MemberEditorBuilder builder() {
        return new MemberEditorBuilder();
    }

    public static class MemberEditorBuilder {
        private String memberName;
        private String password;
        private String role;

        MemberEditorBuilder() {
        }

        public MemberEditorBuilder memberName(final String memberName) {
            if (StringUtils.hasText(memberName)) {
                this.memberName = memberName;
            }
            return this;
        }

        public MemberEditorBuilder password(final String password) {
            if (StringUtils.hasText(password)) {
                this.password = password;
            }
            return this;
        }

        public MemberEditorBuilder role(final String role) {
            if (StringUtils.hasText(role)) {
                this.role = role;
            }
            return this;
        }

        public MemberEditor build() {
            return new MemberEditor(memberName, password, role);
        }
    }
}
