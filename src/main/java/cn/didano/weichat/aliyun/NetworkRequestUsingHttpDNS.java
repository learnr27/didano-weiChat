package cn.didano.weichat.aliyun;



import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.apache.log4j.Logger;

public class NetworkRequestUsingHttpDNS {
	static Logger logger = Logger.getLogger(HttpDNS.class);
	
    private static HttpDNS httpdnsService = HttpDNS.getInstance();
    private static final String[] TEST_URL = {"http://www.aliyun.com", "http://www.taobao.com"};

    public static void main(String[] ss) {
        try {
            HttpDNS.HttpDNSLog.enableLog(true);
            // DegradationFilter用于自定义降级逻辑
            // 通过实现shouldDegradeHttpDNS方法，可以根据需要，选择是否降级
            DegradationFilter filter = new DegradationFilter() {
                @Override
                public boolean shouldDegradeHttpDNS(String hostName) {
                    // 此处可以自定义降级逻辑，例如www.taobao.com不使用HttpDNS解析
                    // 参照HttpDNS API文档，当存在中间HTTP代理时，应选择降级，使用Local DNS
                    return hostName.equals("www.taobao.com");
                }
            };
            // 将filter传进httpdnsService，解析时会回调shouldDegradeHttpDNS方法，判断是否降级
            httpdnsService.setDegradationFilter(filter);

            byte[] buff = new byte[4096];
            HttpURLConnection conn;
            int responseCode;

            for (int i = 0; i < 2; i++) {
                conn = getHttpURLConnection(TEST_URL[i]);
                responseCode = conn.getResponseCode();
                logger.info("HttpDNS Demo"+",Response code: " + responseCode);
                if (responseCode == 200) {
                    InputStream in = conn.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    int len = 0;
                    StringBuilder sb = new StringBuilder();
                    while ((len = dis.read(buff)) != -1) {
                        sb.append(new String(buff, 0, len));
                    }
                    logger.info("HttpDNS Demo"+",Get result: " + sb);
                }
                conn.disconnect();

                Thread.sleep(3000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpURLConnection getHttpURLConnection(String urlString)
            throws IOException {
        URL url = new URL(urlString);
        String originHost = url.getHost();
        HttpURLConnection conn;

        String dstIp = httpdnsService.getIpByHost(url.getHost());
        if (dstIp != null) {
        	 logger.info("HttpDNS Demo" + ",Get IP from HttpDNS, " + url.getHost() + ": " + dstIp);
            urlString = urlString.replaceFirst(url.getHost(), dstIp);
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            // 设置HTTP请求头Host域
            conn.setRequestProperty("Host", originHost);
            return conn;
        } else {
        	 logger.info("HttpDNS Demo"+",Degrade to local DNS.");
            return (HttpURLConnection) url.openConnection();
        }
    }

   
}
