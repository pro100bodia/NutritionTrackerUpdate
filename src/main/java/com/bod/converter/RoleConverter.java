package com.bod.converter;

import com.bod.domain.Role;

import java.beans.PropertyEditorSupport;

public class RoleConverter extends PropertyEditorSupport {
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(Role.valueOf(text));
    }
}
