package com.keenan;

import com.keenan.filter.CMASFilter;

/**
 * Hello world!
 */
public class App {

    static boolean localFile = true;

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        if (localFile) {
            String fileName = "responseTOTAL.csv";
            apiClient.trimCsv(fileName, new CMASFilter());
        } else {
            apiClient.writeJsonToCsv();
        }
    }
}
