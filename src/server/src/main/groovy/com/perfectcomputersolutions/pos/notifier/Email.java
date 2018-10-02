package com.perfectcomputersolutions.pos.notifier;

import com.perfectcomputersolutions.pos.utility.Utility;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Email {

    @javax.validation.constraints.Email
    private String to;

    @NotNull
    @NotEmpty
    private String subject;

    @NotNull
    private String text;

    public Email(
            String to,
            String subject,
            String text) {

        this.to      = to;
        this.subject = subject;
        this.text    = text;

        Utility.validate(this);
    }

    // NOTE - Validation it not happening on purpose.
    // This constructor can only be called from sub-classes
    // or this class. The sub-class is then responsible for
    // validate. We do not validate here because the sub-class
    // may have other fields with validation annotations. If the
    // call to super() had a call to validate(), it would always
    // fail because super() must be the statement in a constructor.
    // Therefore any instance variables that have no initial value will
    // always be null. The annotation processor will pick this up and
    // create a violation - even for private attributes.
    Email(
            String to,
            String subject) {

        this.to      = to;
        this.text    = "";
        this.subject = subject;
    }

    @Override
    public String toString() {
        return Utility.serialize(this);
    }

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
