package com.anthony.torrent.util.http;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.util.concurrent.Callable;

/**
 * Created by chend on 2017/5/20.
 */
public abstract class MultiThreadHttpRequest implements Callable {

    private final CloseableHttpClient httpClient;
    private final HttpContext context;

    public MultiThreadHttpRequest() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
        this.httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        this.context=HttpClientContext.create();
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public HttpContext getContext() {
        return context;
    }
}