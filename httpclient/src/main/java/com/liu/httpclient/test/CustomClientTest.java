package com.liu.httpclient.test;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @className: CustomClientTest
 * @author: yu.liu
 * @date: 2019/12/20 09:51
 * @description: 自定义HTTP头示例
 */
public class CustomClientTest {
    public static void main(String[] args)throws IOException {

        List<Header> defaultHeaders = Arrays.asList(
                new BasicHeader("X-Default-Header", "default header httpclient"));

        CloseableHttpClient httpclient = HttpClients
                .custom()
                .setDefaultHeaders(defaultHeaders)
                .build();

        try {

            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://httpbin.org/headers")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader(HttpHeaders.FROM, "https://memorynotfound.com")
                    .setHeader("X-Custom-Header", "custom header http request")
                    .build();

            System.out.println("Executing request " + request.getRequestLine());

            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(request, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
    }
}
