package com.example.moreveiw.domain.member.editor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class MemberEditor {

    private final String name;
    private final String email;

    public static MemberEditorBuilder builder() {
        return new MemberEditorBuilder();
    }

    public static class MemberEditorBuilder {
        private String name;
        private String email;

        MemberEditorBuilder() {
        }

        public MemberEditorBuilder name(final String name) {
            if (StringUtils.hasText(name)) {
                this.name = name;
            }
            return this;
        }

        public MemberEditorBuilder email(final String email) {
            if (StringUtils.hasText(email)) {
                this.email = email;
            }
            return this;
        }

        public MemberEditor build() {
            return new MemberEditor(name, email);
        }
    }
}
