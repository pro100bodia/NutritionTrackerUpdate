package com.bod.converter;

import com.bod.domain.Gender;

import java.beans.PropertyEditorSupport;

public class GenderConverter extends PropertyEditorSupport {
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(Gender.valueOf(text.toUpperCase()));
    }
}
