package com.keenan.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.keenan.model.input.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CsvModel {
    private String addresses;
    private String code;
    private String cogId;
    private String identifier;
    private String incidents;
    private String msgType;
    private String note;
    private String scope;
    private String sender;
    private String sent;
    private String source;
    private String status;
    private String restriction;
    private String id;
    private String infoLanguage;
    private String infoEventCode;
    private String infoCategory;
    private String infoEvent;
    private String infoAreaDesc;
    private String infoAudience;
    private String infoContact;
    private String infoResponseType;
    private String infoUrgency;
    private String infoSeverity;
    private String infoCertainty;
    private String infoEffective;
    private String infoOnset;
    private String infoExpires;
    private String infoSenderName;
    private String infoHeadline;
    private String infoDescription;
    private String infoInstruction;
    private String infoWeb;
    private String parameterCMAMText;
    private String parameterCMAMLongText;
    private String parameterBlockChannels;
    private String isCMAS;
    private String referenceSender;
    private String referenceIdentifier;
    private String referenceSent;

    public CsvModel() {
    }

    public CsvModel(AlertItem alertItem) {
        this.identifier = alertItem.getIdentifier();
        this.id = alertItem.getId();
        this.sender = alertItem.getSender();
        this.sent = alertItem.getSent();
        this.status = alertItem.getStatus();
        this.msgType = alertItem.getMsgType();
        this.source = alertItem.getSource();
        this.scope = alertItem.getScope();
        this.restriction = alertItem.getRestriction();
        this.addresses = alertItem.getAddresses();
        this.code = StringUtils.join(alertItem.getCode(), "|");
        this.note = alertItem.getNote();
        this.incidents = alertItem.getIncidents();
        this.cogId = alertItem.getCogId();
        if (alertItem.getInfo() != null && alertItem.getInfo().length > 0) {
            AlertInfo info = alertItem.getInfo()[0];
            this.infoLanguage = info.getLanguage();
            if (info.getEventCode() != null && info.getEventCode().length > 0) {
                this.infoEventCode = info.getEventCode()[0].getSAME();
            }
            this.infoCategory = StringUtils.join(info.getCategory(), "|");
            this.infoEvent = info.getEvent();
            this.infoAudience = info.getAudience();
            this.infoContact = info.getContact();
            this.infoResponseType = StringUtils.join(info.getResponseType(), "|");
            this.infoUrgency = info.getUrgency();
            this.infoSeverity = info.getSeverity();
            this.infoCertainty = info.getCertainty();
            this.infoEffective = info.getEffective();
            this.infoOnset = info.getOnset();
            this.infoExpires = info.getExpires();
            this.infoSenderName = info.getSenderName();
            this.infoHeadline = info.getHeadline();
            this.infoDescription = info.getDescription();
            this.infoInstruction = info.getInstruction();
            this.infoWeb = info.getWeb();
            this.isCMAS = "false";
            if (info.getParameter() != null && info.getParameter().length > 0) {
                List<String> blockChannels = new ArrayList<>();
                for (InfoParameter param : info.getParameter()) {
                    if (StringUtils.isNotBlank(param.getCMAMtext())) {
                        this.parameterCMAMText = param.getCMAMtext();
                    }
                    if (StringUtils.isNotBlank(param.getCMAMlongtext())) {
                        this.parameterCMAMLongText = param.getCMAMlongtext();
                    }
                    if (StringUtils.isNotBlank(param.getBlockChannel())) {
                        blockChannels.add(param.getBlockChannel());
                        if (StringUtils.equals(param.getBlockChannel(), "CMAS")) {
                            this.isCMAS = "true";
                        }
                    }
                }
                this.parameterBlockChannels = StringUtils.join(blockChannels, "|");
            }
            if (info.getArea() != null && info.getArea().length > 0) {
                InfoArea area = info.getArea()[0];
                this.infoAreaDesc = area.getAreaDesc();
            }
        }
        if (alertItem.getReferences() != null && alertItem.getReferences().length > 0) {
            AlertReference reference = alertItem.getReferences()[0];
            this.referenceSender = reference.getSender();
            this.referenceIdentifier = reference.getIdentifier();
            this.referenceSent = reference.getSent();
        }
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCogId() {
        return cogId;
    }

    public void setCogId(String cogId) {
        this.cogId = cogId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIncidents() {
        return incidents;
    }

    public void setIncidents(String incidents) {
        this.incidents = incidents;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoLanguage() {
        return infoLanguage;
    }

    public void setInfoLanguage(String infoLanguage) {
        this.infoLanguage = infoLanguage;
    }

    public String getInfoEventCode() {
        return infoEventCode;
    }

    public void setInfoEventCode(String infoEventCode) {
        this.infoEventCode = infoEventCode;
    }

    public String getInfoCategory() {
        return infoCategory;
    }

    public void setInfoCategory(String infoCategory) {
        this.infoCategory = infoCategory;
    }

    public String getInfoEvent() {
        return infoEvent;
    }

    public void setInfoEvent(String infoEvent) {
        this.infoEvent = infoEvent;
    }

    public String getInfoAreaDesc() {
        return infoAreaDesc;
    }

    public void setInfoAreaDesc(String infoAreaDesc) {
        this.infoAreaDesc = infoAreaDesc;
    }

    public String getInfoAudience() {
        return infoAudience;
    }

    public void setInfoAudience(String infoAudience) {
        this.infoAudience = infoAudience;
    }

    public String getInfoContact() {
        return infoContact;
    }

    public void setInfoContact(String infoContact) {
        this.infoContact = infoContact;
    }

    public String getInfoResponseType() {
        return infoResponseType;
    }

    public void setInfoResponseType(String infoResponseType) {
        this.infoResponseType = infoResponseType;
    }

    public String getInfoUrgency() {
        return infoUrgency;
    }

    public void setInfoUrgency(String infoUrgency) {
        this.infoUrgency = infoUrgency;
    }

    public String getInfoSeverity() {
        return infoSeverity;
    }

    public void setInfoSeverity(String infoSeverity) {
        this.infoSeverity = infoSeverity;
    }

    public String getInfoCertainty() {
        return infoCertainty;
    }

    public void setInfoCertainty(String infoCertainty) {
        this.infoCertainty = infoCertainty;
    }

    public String getInfoEffective() {
        return infoEffective;
    }

    public void setInfoEffective(String infoEffective) {
        this.infoEffective = infoEffective;
    }

    public String getInfoOnset() {
        return infoOnset;
    }

    public void setInfoOnset(String infoOnset) {
        this.infoOnset = infoOnset;
    }

    public String getInfoExpires() {
        return infoExpires;
    }

    public void setInfoExpires(String infoExpires) {
        this.infoExpires = infoExpires;
    }

    public String getInfoSenderName() {
        return infoSenderName;
    }

    public void setInfoSenderName(String infoSenderName) {
        this.infoSenderName = infoSenderName;
    }

    public String getInfoHeadline() {
        return infoHeadline;
    }

    public void setInfoHeadline(String infoHeadline) {
        this.infoHeadline = infoHeadline;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public void setInfoDescription(String infoDescription) {
        this.infoDescription = infoDescription;
    }

    public String getInfoInstruction() {
        return infoInstruction;
    }

    public void setInfoInstruction(String infoInstruction) {
        this.infoInstruction = infoInstruction;
    }

    public String getInfoWeb() {
        return infoWeb;
    }

    public void setInfoWeb(String infoWeb) {
        this.infoWeb = infoWeb;
    }

    public String getParameterCMAMText() {
        return parameterCMAMText;
    }

    public void setParameterCMAMText(String parameterCMAMText) {
        this.parameterCMAMText = parameterCMAMText;
    }

    public String getParameterCMAMLongText() {
        return parameterCMAMLongText;
    }

    public void setParameterCMAMLongText(String parameterCMAMLongText) {
        this.parameterCMAMLongText = parameterCMAMLongText;
    }

    public String getParameterBlockChannels() {
        return parameterBlockChannels;
    }

    public void setParameterBlockChannels(String parameterBlockChannels) {
        this.parameterBlockChannels = parameterBlockChannels;
    }

    public String getIsCMAS() {
        return isCMAS;
    }

    public void setIsCMAS(String isCMAS) {
        this.isCMAS = isCMAS;
    }

    public String getReferenceSender() {
        return referenceSender;
    }

    public void setReferenceSender(String referenceSender) {
        this.referenceSender = referenceSender;
    }

    public String getReferenceIdentifier() {
        return referenceIdentifier;
    }

    public void setReferenceIdentifier(String referenceIdentifier) {
        this.referenceIdentifier = referenceIdentifier;
    }

    public String getReferenceSent() {
        return referenceSent;
    }

    public void setReferenceSent(String referenceSent) {
        this.referenceSent = referenceSent;
    }
}
