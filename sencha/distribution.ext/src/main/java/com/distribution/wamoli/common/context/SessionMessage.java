package com.distribution.wamoli.common.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Component
public class SessionMessage implements MessageService {

    @Autowired
    HttpServletRequest request;

    private String PREFIX = "MESSAGE_SESSION_";

    @Override
    public void set(String key,MessageInfo value) {
        request.getSession().setAttribute(PREFIX+key , value);
    }

    @Override
    public MessageInfo get(String key) {
        MessageInfo messageInfo= (MessageInfo) request.getSession().getAttribute(PREFIX+key);
        request.getSession().removeAttribute(PREFIX);
        return messageInfo;
    }
}
