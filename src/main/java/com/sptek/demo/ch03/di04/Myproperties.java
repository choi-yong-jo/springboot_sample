package com.sptek.demo.ch03.di04;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="mysite")
public class Myproperties {
    private String domain;
    private String email;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
