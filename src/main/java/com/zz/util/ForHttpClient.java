package com.zz.util;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.client.HttpClient;

public class ForHttpClient {

    private static HttpClient httpClient;

    private ForHttpClient() {
    }

    public static HttpClient getHttpClientInstance() {

        if (httpClient == null) {
            httpClient = new DefaultHttpClient();
        }

        return httpClient;
    }

}
