package com.perfectcomputersolutions.pos.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SimpleMessage {

    // TODO - Swagger annotations

    @NotNull
    @NotEmpty
    @Email
    private String to;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 78)
    private String subject;

    @NotNull
    @NotEmpty
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
