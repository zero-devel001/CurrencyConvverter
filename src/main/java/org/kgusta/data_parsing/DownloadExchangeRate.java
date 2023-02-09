package org.kgusta.data_parsing;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DownloadExchangeRate {

    public static void main(String[] args) {
        loadDailyExchangeRate();
        loadWeeklyExchangeRate();
    }
    public static void loadDailyExchangeRate() {
        try {
            new DownloadExchangeRate().downloadFile("https://www.nbkr.kg/XML/daily.xml", "daily");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void loadWeeklyExchangeRate() {
        try {
            new DownloadExchangeRate().downloadFile("https://www.nbkr.kg/XML/weekly.xml", "weekly");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void downloadFile(String fileUrl,String period) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(fileUrl)).build();

        String folder = "./src/main/resources/data/" + period + "/";
        Path path = Path.of( folder  + period + ".xml"); // Path to save file
        HttpResponse<Path> response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(path));

        int statusCode = response.statusCode();

        if (statusCode != 200) {
            throw new IOException("Failed to download file with status code: " + statusCode);
        }
    }



}
