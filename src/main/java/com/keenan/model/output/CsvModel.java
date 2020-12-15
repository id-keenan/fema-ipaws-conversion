package com.keenan.model.output;

import com.keenan.model.input.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public String getIsCMAS() {
        return isCMAS;
    }

    public String getAddresses() {
        return addresses;
    }

    public String getCode() {
        return code;
    }

    public String getCogId() {
        return cogId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getIncidents() {
        return incidents;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getNote() {
        return note;
    }

    public String getScope() {
        return scope;
    }

    public String getSender() {
        return sender;
    }

    public String getSent() {
        return sent;
    }

    public String getSource() {
        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getRestriction() {
        return restriction;
    }

    public String getId() {
        return id;
    }

    public String getInfoLanguage() {
        return infoLanguage;
    }

    public String getInfoEventCode() {
        return infoEventCode;
    }

    public String getInfoCategory() {
        return infoCategory;
    }

    public String getInfoEvent() {
        return infoEvent;
    }

    public String getInfoAreaDesc() {
        return infoAreaDesc;
    }

    public String getInfoAudience() {
        return infoAudience;
    }

    public String getInfoContact() {
        return infoContact;
    }

    public String getInfoResponseType() {
        return infoResponseType;
    }

    public String getInfoUrgency() {
        return infoUrgency;
    }

    public String getInfoSeverity() {
        return infoSeverity;
    }

    public String getInfoCertainty() {
        return infoCertainty;
    }

    public String getInfoEffective() {
        return infoEffective;
    }

    public String getInfoOnset() {
        return infoOnset;
    }

    public String getInfoExpires() {
        return infoExpires;
    }

    public String getInfoSenderName() {
        return infoSenderName;
    }

    public String getInfoHeadline() {
        return infoHeadline;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public String getInfoInstruction() {
        return infoInstruction;
    }

    public String getInfoWeb() {
        return infoWeb;
    }

    public String getParameterCMAMText() {
        return parameterCMAMText;
    }

    public String getParameterCMAMLongText() {
        return parameterCMAMLongText;
    }

    public String getParameterBlockChannels() {
        return parameterBlockChannels;
    }

    public String getReferenceSender() {
        return referenceSender;
    }

    public String getReferenceIdentifier() {
        return referenceIdentifier;
    }

    public String getReferenceSent() {
        return referenceSent;
    }
}
