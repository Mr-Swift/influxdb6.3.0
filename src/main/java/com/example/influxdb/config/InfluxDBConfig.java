package com.example.influxdb.config;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class InfluxDBConfig {
    @Value("${influx2.url}")
    private String url;

    @Value("${influx2.org}")
    private String org;

    @Value("${influx2.bucket}")
    private String bucket;

    @Value("${influx2.token}")
    private String token;

    @Value("${influx2.logLevel}")
    private String logLevel;

    @Value("${influx2.readTimeout}")
    private String readTimeout;

    @Value("${influx2.writeTimeout}")
    private String writeTimeout;

    @Value("${influx2.connectTimeout}")
    private String connectTimeout;


    @Bean
    public InfluxDBClient influxDBClient() {

        InputStream inputStream = this.getClass().getResourceAsStream("/application.properties");
        InfluxDBClient influxDBClient = null;

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("influx2.url");
            String org = properties.getProperty("influx2.org");
            String bucket = properties.getProperty("influx2.bucket");
            String token = properties.getProperty("influx2.token");
            String logLevel = properties.getProperty("influx2.logLevel");
            String readTimeout = properties.getProperty("influx2.readTimeout");
            String writeTimeout = properties.getProperty("influx2.writeTimeout");
            String connectTimeout = properties.getProperty("influx2.connectTimeout");
            String precision = properties.getProperty("influx2.precision");
            String consistency = properties.getProperty("influx2.consistency");
            String clientType = properties.getProperty("influx2.clientType");


            InfluxDBClientOptions options = InfluxDBClientOptions.builder()
                    .url(this.url)
                    .authenticateToken(this.token.toCharArray())
                    .org(this.org)
                    .logLevel(LogLevel.BASIC)
                    .build();

            //完全版配置
            //influxDBClient = InfluxDBClientFactory
            //        .create(url + "?readTimeout=" + readTimeout + "&connectTimeout=" + connectTimeout + "&logLevel=" + logLevel, token.toCharArray());


            //简易版配置(没有超时设置)
            influxDBClient = InfluxDBClientFactory.create(options);

            influxDBClient.enableGzip();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return influxDBClient;
    }

}
