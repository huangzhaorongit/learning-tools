package com.distribution.wamoli.web.rest.vm;

import com.distribution.wamoli.config.Constants;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

/**
 * View Model object for storing a user's credentials.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "password",
    "rememberMe"
})
@XmlRootElement(name = "loginVM", namespace = "http://www.distribution.com/modules/ws")
public class LoginVM {
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @XmlElement(required = true)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @XmlElement(required = true)
    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @XmlElement(required = false)
    private Boolean rememberMe = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginVM{" +
            "password='*****'" +
            ", username='" + username + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}
