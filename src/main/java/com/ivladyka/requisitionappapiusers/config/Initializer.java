package com.ivladyka.requisitionappapiusers.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Initializer extends AbstractHttpSessionApplicationInitializer {
    public Initializer() {
        super(WebConfig.class);
    }
}
