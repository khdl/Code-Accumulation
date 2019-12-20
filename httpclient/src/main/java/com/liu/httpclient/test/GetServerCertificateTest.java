package com.liu.httpclient.test;

import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * @className: GetServerCertificateTest
 * @author: yu.liu
 * @date: 2019/12/19 16:13
 * @description:  HTTP获取服务器证书示例
 */
public class GetServerCertificateTest {
    public static final String PEER_CERTIFICATES = "PEER_CERTIFICATES";

    public static void main(String... args) throws IOException {

        // create http response certificate interceptor
        HttpResponseInterceptor certificateInterceptor = (httpResponse, context) -> {
            ManagedHttpClientConnection routedConnection = (ManagedHttpClientConnection)context.getAttribute(HttpCoreContext.HTTP_CONNECTION);
            SSLSession sslSession = routedConnection.getSSLSession();
            if (sslSession != null) {
                Certificate[] certificates = sslSession.getPeerCertificates();
                context.setAttribute(PEER_CERTIFICATES, certificates);
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().addInterceptorLast(certificateInterceptor).build();
        try {

            HttpGet httpget = new HttpGet("https://www.baidu.com");
            System.out.println("Executing request " + httpget.getRequestLine());

            HttpContext context = new BasicHttpContext();
            httpClient.execute(httpget, context);

            Certificate[] peerCertificates = (Certificate[])context.getAttribute(PEER_CERTIFICATES);

            for (Certificate certificate : peerCertificates){
                X509Certificate real = (X509Certificate) certificate;
                System.out.println("----------------------------------------");
                System.out.println("Type: " + real.getType());
                System.out.println("Signing Algorithm: " + real.getSigAlgName());
                System.out.println("IssuerDN Principal: " + real.getIssuerX500Principal());
                System.out.println("SubjectDN Principal: " + real.getSubjectX500Principal());
                System.out.println("Not After: " + DateUtils.formatDate(real.getNotAfter(), "dd-MM-yyyy"));
                System.out.println("Not Before: " + DateUtils.formatDate(real.getNotBefore(), "dd-MM-yyyy"));
            }

        } finally {
            httpClient.close();
        }
    }


}
