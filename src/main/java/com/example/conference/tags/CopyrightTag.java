package com.example.conference.tags;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

/**
 * Copyright custom tag.
 */

public final class CopyrightTag extends TagSupport {
    private static final String MESSAGE = "<h6 style=\"text-align: center\">" + "© O.V. Maliarchuk, 2022"
            +  "</h6>";


    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(MESSAGE);
        } catch (IOException e) {
            //logger
        }
        return SKIP_BODY;
    }
}