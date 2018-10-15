package com.perfectcomputersolutions.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.perfectcomputersolutions.pos.utility.Utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity(name = "email")
public class Email extends ModelEntity {

    @NotNull
    private Timestamp created;

    @NotNull
    @NotEmpty
    @Transient
    private String template;

    @NotNull
    @NotEmpty
    @Column(name = "email")
    @javax.validation.constraints.Email
    private String to;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 78)
    @Column(name = "title")
    private String subject;

    @NotNull
    @NotEmpty
    @Column(name = "content", columnDefinition = "TEXT")
    private String text;

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

    @JsonIgnore
    public String getText() {
        return text;
    }

    @JsonProperty
    public void setText(String text) {
        this.text = text;
    }

    @JsonIgnore
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
