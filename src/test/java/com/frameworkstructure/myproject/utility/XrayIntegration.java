package com.frameworkstructure.myproject.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class XrayIntegration {
    private static final String XRAY_API_URL = "https://xray.cloud.xpand-it.com/api/v2/import/execution";
    private static final String AUTH_URL = "https://xray.cloud.getxray.app/api/v2/authenticate";
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String AUTH_TOKEN ="cgvh" ;

    private static String authenticate() throws Exception {
        String authPayload = String.format("{\"client_id\":\"%s\",\"client_secret\":\"%s\"}", CLIENT_ID, CLIENT_SECRET);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(AUTH_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(authPayload))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().replace("\"", "");
    }

    private static void updateTestExecutionStatus(String token, String testExecutionKey, String testKey, String status) throws Exception {
        String executionPayload = String.format("{\"testExecutionKey\":\"%s\",\"tests\":[{\"testKey\":\"%s\",\"status\":\"%s\"}]}", testExecutionKey, testKey, status);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(XRAY_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(executionPayload))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void attachScreenshotToXray(String issueKey, File screenshot) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(XRAY_API_URL);

        String screenshotBase64 = encodeFileToBase64Binary(screenshot);
        String json = String.format("{ \"testExecutionKey\": \"%s\", \"info\": { \"summary\": \"Test execution with screenshot\", \"description\": \"Execution with screenshot attached\" }, \"tests\": [{ \"testKey\": \"%s\", \"evidences\": [{ \"data\": \"%s\", \"filename\": \"screenshot.png\", \"contentType\": \"image/png\" }] }] }", issueKey, issueKey, screenshotBase64);

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + AUTH_TOKEN);

        client.execute(httpPost);
        client.close();
    }

    private static String encodeFileToBase64Binary(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) throws Exception {
        String token = authenticate();
        updateTestExecutionStatus(token, "TEST_EXECUTION_KEY", "TEST_KEY", "PASS");
    }
}
