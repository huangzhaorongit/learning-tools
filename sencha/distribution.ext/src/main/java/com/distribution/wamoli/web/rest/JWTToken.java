package com.distribution.wamoli.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

/**
 * Object to return as body in JWT Authentication.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idToken"
})
@XmlRootElement(name = "jwtToken", namespace = "http://www.distribution.com/modules/ws")
public class JWTToken {

    @XmlElement(required = false)
    private String idToken;

    public JWTToken(){
    }

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
