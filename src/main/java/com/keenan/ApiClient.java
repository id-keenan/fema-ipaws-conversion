package com.keenan;

import com.keenan.model.input.AlertItem;
import com.keenan.model.output.CsvModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;


public class ApiClient {

    public static int INCREMENT = 1000;
    private int written;
    private CsvSchema csvSchema;
    private CsvMapper csvMapper;

    public ApiClient() {
        // Default constructor
    }

    public void writeJsonToCsv() {
        String response = "";
        written = 0;
        String columnString = "id," +
                "addresses," +
                "isCMAS," +
                "code," +
                "cogId," +
                "identifier," +
                "incidents," +
                "msgType," +
                "scope," +
                "sender," +
                "sent," +
                "note," +
                "source," +
                "actual," +
                "status," +
                "parameterCMAMText," +
                "parameterCMAMLongText," +
                "parameterBlockChannels," +
                "infoEventCode," +
                "infoLanguage," +
                "infoCategory," +
                "infoEvent," +
                "infoAreaDesc," +
                "infoAudience," +
                "infoContact," +
                "infoResponseType," +
                "infoUrgency," +
                "infoSeverity," +
                "infoCertainty," +
                "infoEffective," +
                "infoOnset," +
                "infoExpires," +
                "infoSenderName," +
                "infoHeadline," +
                "infoDescription," +
                "infoInstruction," +
                "infoWeb," +
                "referenceSender," +
                "referenceIdentifier," +
                "referenceSent," +
                "restriction";
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        for (String column : columnString.split(Pattern.quote(","))) {
            csvSchemaBuilder.addColumn(column);
        }
        csvSchema = csvSchemaBuilder.build().withHeader();
        csvMapper = new CsvMapper();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String countEndpoint = "https://www.fema.gov/api/open/v1/IpawsArchivedAlerts?$inlinecount=allpages&$select=id&$top=1";
            String countResponse = Request.Get(countEndpoint).execute().returnContent().asString();
            JsonNode countJson = objectMapper.readTree(countResponse);
            JsonNode metadataNode = countJson.get("metadata");
            int total = 0;
            if (metadataNode != null) {
                JsonNode countNode = metadataNode.get("count");
                if (countNode != null) {
                    total = countNode.asInt();
                }
            }
            if (total == 0) {
                System.out.println("Could not retrieve total count.");
                return;
            } else {
                System.out.println("Total size to write: " + total);
            }
            int skip = 0;
            int chunkSize = 5000;
            List<CsvModel> chunkOutput = new ArrayList<>();
            List<CsvModel> totalOutput = new ArrayList<>();
            while (skip <= total) {
                System.out.println("Requesting 1000 results with skip of " + skip);
                long startTime = System.currentTimeMillis();
                String url = "https://www.fema.gov/api/open/v1/IpawsArchivedAlerts?$top=" + INCREMENT + "&$skip=" + skip + "&$orderby=sent%20desc";
                try {
                    response = Request.Get(url).socketTimeout(30000).execute().returnContent().asString();
                } catch (Exception e) {
                    System.out.println("Initial request failed, retrying - error message: " + e.getMessage());
                    response = Request.Get(url).socketTimeout(30000).execute().returnContent().asString();
                }
                System.out.println("Execution time: " + (System.currentTimeMillis() - startTime) + "ms");
                int numAdded = 0;
                try {
                    JsonNode jsonTree = objectMapper.readTree(response);
                    JsonNode alertArray = jsonTree.get("IpawsArchivedAlerts");
                    if (alertArray.isArray()) {
                        Iterator<JsonNode> elements = alertArray.elements();
                        while (elements.hasNext()) {
                            AlertItem alertItem = objectMapper.readValue(elements.next().toString(), AlertItem.class);
                            CsvModel alertCsvModel = new CsvModel(alertItem);
                            if (StringUtils.isNotBlank(alertCsvModel.getParameterCMAMText())) {
                                chunkOutput.add(alertCsvModel);
                                totalOutput.add(alertCsvModel);
                                numAdded++;
                            }
                        }
                    }
                } catch (JsonProcessingException e) {
                    System.out.println("Error in JSON mapping");
                    e.printStackTrace();
                }
                System.out.println("Found " + numAdded + " results in this set.");
                System.out.println("Total size of output: " + chunkOutput.size());
                if (chunkOutput.size() > chunkSize) {
                    writeToFile("response" + written + ".csv", chunkOutput);
                    System.out.println("Total Remaining: " + (total - written));
                    chunkOutput = new ArrayList<>();
                }
                skip = skip + INCREMENT;
            }
            if (chunkOutput.size() > 0) {
                System.out.println("Writing final " + chunkOutput.size() + " results");
                writeToFile("responseFINAL.csv", chunkOutput);
                System.out.println("Final chunk write complete");
            }
            if (totalOutput.size() > 0) {
                System.out.println("Writing total " + totalOutput.size() + " results");
                writeToFile("responseTOTAL.csv", totalOutput);
                System.out.println("Total write complete");
            }
        } catch (Exception e) {
            System.out.println("Error in API call");
            e.printStackTrace();
        }
        System.out.println("Finished.");
    }

    private void writeToFile(String fileName, List<CsvModel> output) throws IOException {
        written += output.size();
        System.out.println("Total Written: " + written);
        csvMapper.writerFor(CsvModel[].class)
                .with(csvSchema)
                .writeValue(new File(fileName), output.toArray(new CsvModel[]{}));
    }
}
