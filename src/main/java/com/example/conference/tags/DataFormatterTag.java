package com.example.conference.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *     Defines tag that can be used to print object of {@link LocalDateTime} class to custom format
 */
public class DataFormatterTag extends TagSupport {
    /**
     * <p>
     *     Date that will be formatted
     * </p>
     */
    private LocalDateTime date;

    /**
     * <p>
     *     Date format
     * </p>
     */
    private String format;

    /**
     * <p>
     *     Formats date
     * </p>
     * @return SKIP_BODY
     */
    @Override
    public int doStartTag() {
        initDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDate = date.format(formatter);
        JspWriter out = pageContext.getOut();
        try {
            out.print(formattedDate);
        } catch (IOException exception) {
           // exception.printStackTrace();
            //log
        }
        resetData();
        return SKIP_BODY;
    }

    /**
     * <p>
     *     Inits optional parameters
     * </p>
     */
    private void initDate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }

    /**
     * <p>
     *     Resets optional parameters to their initial values
     * </p>
     */
    private void resetData() {
        date = null;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
