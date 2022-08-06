package com.example.influxdb.config.activate;

import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.OpenOption;
import java.time.OffsetDateTime;
import java.util.Optional;

@Component
public class DeleteData {
    @Value("${influx2.org}")
    private String org;

    private DeleteApi deleteApi;

    //构造方法
    public DeleteData(InfluxDBClient influxDBClient) {
        Optional.ofNullable(influxDBClient).ifPresent(item->{
            this.setDeleteApi(item.getDeleteApi());
        });
    }

    public DeleteData setDeleteApi(DeleteApi deleteApi) {
        this.deleteApi = deleteApi;
        return this;
    }

    /**
     * 删除
     *
     * @param bucketName 存储桶名称
     * @param start 开始时间
     * @param stop 结束时间
     */
    public void delete(String bucketName, OffsetDateTime start,OffsetDateTime stop){
        deleteApi.delete(start, stop, "", bucketName, org);
    }

    /**
     * 删除
     *
     * @param bucketName 存储桶名称
     * @param predicate 删除条件（key1="value1" AND key2="value2"）
     * @param start 开始时间
     * @param stop 结束时间
     */
    public void delete(String bucketName,String predicate, OffsetDateTime start,OffsetDateTime stop){
        deleteApi.delete(start, stop, predicate, bucketName, org);
    }
}
