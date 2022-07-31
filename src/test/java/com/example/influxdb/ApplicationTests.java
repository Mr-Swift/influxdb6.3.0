package com.example.influxdb;

import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    public InfluxDBClient influxDBClient;

    @Test
    void contextLoads() {
        DeleteApi deleteApi = influxDBClient.getDeleteApi();

        System.out.println(deleteApi);

    }

}
