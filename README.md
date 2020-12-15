# FEMA IPAWS API Data Type Conversion

## Overview
The purpose of this repository is to provide a simple way to iterate through the total results from the IPAWS API provided by FEMA.

API Used: https://www.fema.gov/openfema-data-page/ipaws-archived-alerts--v1

The project will get the maximum count on alerts stored in the API and then page through them 1000 at a time until the 
maximum is reached. Each item will be added to an array of models that translate the JSON data to CSV. It has to 
do a bit of mapping due to the inherent difference between JSON storage (sub-objects with generic keys) and CSV 
(flat with set columns). It will write the data out in chunks (of configurable size, set to 100,000 now). There is a filter
set up to only select results with the property 'CMAMtext' populated. 

## Technical Stack
* Java 8/11
* Maven
* Jackson Databind
  * CSV and JSON mapping 
* Apache Fluent HTTP Requests

## Technical Description
#### Building
The project is Java-based and uses Maven for dependency management. There isn't an artifact generated from the build
process, it is just something that was run directly in an IDE using the Main method in App.java.

#### API Interaction
The program makes requests to the API using an HTTP client that does basic GET requests with parameters passed.
There is an initial request to the API to get the total current count of alerts stored in the API, then using that
total count it pages through the API 1000 items at a time (the total amount allowed per request by the API). Using
Jackson Databind it maps the response into an array of the AlertItem class, which stores the majority of the data for
each object. The more complex JSON data structure is then mapped into CsvModel, which uses prefixes in the names to
represent which object the property came from. In the end the results get written out to files directly in the project, 
which can then be imported into Excel for analysis. There is an additional feature that will let you interact with a CSV
file already stored in the file system to further filter the results. 
