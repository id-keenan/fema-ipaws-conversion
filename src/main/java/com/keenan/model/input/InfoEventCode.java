package com.keenan.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoEventCode {

    @JsonProperty("SAME")
    private String SAME;

    public String getSAME() {
        return SAME;
    }

    public void setSAME(String SAME) {
        this.SAME = SAME;
    }
}
