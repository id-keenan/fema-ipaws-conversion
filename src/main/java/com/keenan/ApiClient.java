package com.keenan;

import com.keenan.model.input.AlertItem;
import com.keenan.model.output.CsvModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;


public class ApiClient {

    public static int INCREMENT = 1000;

    public ApiClient() {
        // Default constructor
    }

    public void writeJsonToCsv() {
        String response = "";
        String columnString = "id," + "addresses," +
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
            int chunkSize = 100000;
            List<CsvModel> output = new ArrayList<>();
            int written = 0;
            while (skip <= total) {
                response = Request.Get("https://www.fema.gov/api/open/v1/IpawsArchivedAlerts?$top=" + INCREMENT + "&$skip=" + skip + "&$orderby=sent%20desc").execute().returnContent().asString();
                try {
                    JsonNode jsonTree = objectMapper.readTree(response);
                    JsonNode alertArray = jsonTree.get("IpawsArchivedAlerts");
                    if (alertArray.isArray()) {
                        Iterator<JsonNode> elements = alertArray.elements();
                        while (elements.hasNext()) {
                            AlertItem alertItem = objectMapper.readValue(elements.next().toString(), AlertItem.class);
                            CsvModel alertCsvModel = new CsvModel(alertItem);
                            output.add(alertCsvModel);
                        }
                    }
                } catch (JsonProcessingException e) {
                    System.out.println("Error in JSON mapping");
                    e.printStackTrace();
                }
                if (output.size() > 0 && output.size() % chunkSize == 0) {
                    CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
                    for (String column : columnString.split(Pattern.quote(","))) {
                        csvSchemaBuilder.addColumn(column);
                    }
                    CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
                    CsvMapper csvMapper = new CsvMapper();
                    System.out.println("Writing " + (skip + INCREMENT) + " results");
                    written += output.size();
                    System.out.println("Total Written: " + written);
                    System.out.println("Total Remaining: " + (total - written));
                    csvMapper.writerFor(CsvModel[].class)
                            .with(csvSchema)
                            .writeValue(new File("response" + (skip + INCREMENT) + ".csv"), output.toArray(new CsvModel[]{}));
                    output = new ArrayList<>();
                }
                skip = skip + INCREMENT;
            }
        } catch (Exception e) {
            System.out.println("Error in API call");
            e.printStackTrace();
        }
    }
}
