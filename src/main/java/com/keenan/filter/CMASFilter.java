package com.keenan.filter;

import com.keenan.model.output.CsvModel;
import org.apache.commons.lang3.StringUtils;

public class CMASFilter implements CsvFilter {

    public CMASFilter() {
    }

    @Override
    public boolean matchesFilter(CsvModel input) {
        return StringUtils.equals("true", input.getIsCMAS());
    }
}
