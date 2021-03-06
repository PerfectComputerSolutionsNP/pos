package com.perfectcomputersolutions.pos.payload;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SimpleMessage {

    @NotNull
    @NotEmpty
    @Email
    @ApiModelProperty(notes = "A valid destination email")
    private String to;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 78)
    @ApiModelProperty(notes = "Non-empty email subject")
    private String subject;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Non-empty email body")
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
