package com.keenan.filter;

import com.keenan.model.output.CsvModel;

public interface CsvFilter {
    boolean matchesFilter(CsvModel input);
}
