package com.liu.httpclient.test;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.net.URI;
import java.util.List;

/**
 * @className: RedictTest
 * @author: yu.liu
 * @date: 2019/12/20 09:29
 * @description: 重定向处理示例
 */
public class RedictTest {
    public static void main(String[] args)throws Exception{
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        try {
            HttpClientContext context = HttpClientContext.create();
            HttpGet httpGet = new HttpGet("http://httpbin.org/redirect/3");
            System.out.println("Executing request " + httpGet.getRequestLine());
            System.out.println("----------------------------------------");

            httpclient.execute(httpGet, context);
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpGet.getURI(), target, redirectLocations);
            System.out.println("Final HTTP location: " + location.toASCIIString());

        } finally {
            httpclient.close();
        }
    }
}
