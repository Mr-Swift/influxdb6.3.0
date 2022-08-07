package com.example.influxdb.base.activate;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class WriteMeasurement {
    @Value("${influx2.org}")
    private String org;

    @Resource
    private InfluxDBClient influxDBClient;

    /**
     * 批量写入
     *
     * @param bucketName 存储桶名称
     * @param list 待写入对象集合
     * @param <T>
     */
    public <T> void writePoint(String bucketName, List<T> list){
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        if (!CollectionUtils.isEmpty(list)) {
            writeApi.writeMeasurements(bucketName, org, WritePrecision.S, list);
        }
    }
}
