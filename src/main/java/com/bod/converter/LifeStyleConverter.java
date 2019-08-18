package com.bod.converter;

import com.bod.domain.LifeStyle;

import java.beans.PropertyEditorSupport;

public class LifeStyleConverter extends PropertyEditorSupport {
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(LifeStyle.valueOf(text));
    }
}
