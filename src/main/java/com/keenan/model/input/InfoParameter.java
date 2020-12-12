package com.keenan.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoParameter {
    @JsonProperty("CMAMtext")
    private String CMAMtext;

    @JsonProperty("CMAMlongtext")
    private String CMAMlongtext;

    @JsonProperty("BLOCKCHANNEL")
    private String blockChannel;

    public String getCMAMtext() {
        return CMAMtext;
    }

    public void setCMAMtext(String CMAMtext) {
        this.CMAMtext = CMAMtext;
    }

    public String getCMAMlongtext() {
        return CMAMlongtext;
    }

    public void setCMAMlongtext(String CMAMlongtext) {
        this.CMAMlongtext = CMAMlongtext;
    }

    public String getBlockChannel() {
        return blockChannel;
    }

    public void setBlockChannel(String blockChannel) {
        this.blockChannel = blockChannel;
    }
}
