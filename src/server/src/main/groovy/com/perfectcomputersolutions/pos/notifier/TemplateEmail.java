package com.perfectcomputersolutions.pos.notifier;

import com.perfectcomputersolutions.pos.utility.Utility;

import javax.validation.constraints.NotNull;

public class TemplateEmail extends Email {

    @NotNull private final Object object;
    @NotNull private final String template;

    public TemplateEmail(
            Object object,
            String to,
            String subject,
            String template) {

        super(to, subject);

        this.template = template;
        this.object   = object;

        Utility.validate(this);
    }

    public final Object getObject() {
        return object;
    }

    public final String getTemplate() {
        return template;
    }
}
