package com.keenan.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertItem {
    private String originalMessage;
    private String identifier;
    private String sender;
    private String sent;
    private String status;
    private String msgType;
    private String source;
    private String scope;
    private String restriction;
    private String addresses;
    private String[] code;
    private String note;
    private String incidents;
    private String cogId;
    private String id;
    private AlertInfo[] info;
    private AlertReference[] references;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlertReference[] getReferences() {
        return references;
    }

    public void setReferences(AlertReference[] references) {
        this.references = references;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String[] getCode() {
        return code;
    }

    public void setCode(String[] code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIncidents() {
        return incidents;
    }

    public void setIncidents(String incidents) {
        this.incidents = incidents;
    }

    public String getCogId() {
        return cogId;
    }

    public void setCogId(String cogId) {
        this.cogId = cogId;
    }

    public AlertInfo[] getInfo() {
        return info;
    }

    public void setInfo(AlertInfo[] info) {
        this.info = info;
    }
}
