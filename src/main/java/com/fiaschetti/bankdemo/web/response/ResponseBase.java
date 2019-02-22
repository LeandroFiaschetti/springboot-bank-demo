package com.fiaschetti.bankdemo.web.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseBase implements Serializable {

    private static final long serialVersionUID = 8969711645519539903L;

    private boolean response;
    private String generalMessage;
    private Object entity;
    private List<String> errors;

    public ResponseBase() {
        response = false;
        generalMessage = "";
        errors = new ArrayList<>();
    }

    public ResponseBase(boolean response, Object entity, String generalMessage, List<String> errors) {
        this.response = response;
        this.entity = entity;
        this.generalMessage = generalMessage;
        this.errors = errors;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public void setGeneralMessage(String generalMessage) {
        this.generalMessage = generalMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
