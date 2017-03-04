package com.ragdroid.mockstar.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.QueueDispatcher;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by garimajain on 05/03/17.
 */

public class LocalResponseDispatcher extends QueueDispatcher {

    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        MockResponse mockResponse = new MockResponse();
        String scenario = getScenario(request);
        if (scenario != null) {
            try {
                mockResponse.setBody(readFile(scenario));
                mockResponse.setResponseCode(200);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mockResponse;
    }

    private String getScenario(RecordedRequest request) {
        String scenario = "";

        String path = request.getPath();
        String requestedMethod = request.getMethod().toLowerCase();

        scenario += requestedMethod + path.replace("/", "_") + ".json";
        return scenario;
    }

    private String readFile(String jsonFileName) throws IOException {
        InputStream inputStream = LocalResponseDispatcher.class.getResourceAsStream("/"
                + jsonFileName);
        if (inputStream == null) {
            throw new NullPointerException("Have you added the local resource correctly?, "
                    + "Hint: name it as: " + jsonFileName);
        }
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream);
            BufferedReader rdr = new BufferedReader(isr);
            for (int c; (c = rdr.read()) != -1;) {
                sb.append((char) c);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            if (isr != null) {
                isr.close();
            }
        }
        return sb.toString();
    }
}

