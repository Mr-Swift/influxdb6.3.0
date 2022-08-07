package com.example.influxdb.base.activate;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.query.dsl.Flux;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryMeasurement {
    @Value("${influx2.org}")
    private String org;

    private QueryApi queryApi;

    public QueryMeasurement(InfluxDBClient influxDBClient) {
        Optional.ofNullable(influxDBClient).ifPresent(item->{
            this.setQueryApi(item.getQueryApi());
        });
    }

    public QueryMeasurement setQueryApi(QueryApi queryApi) {
        this.queryApi = queryApi;
        return this;
    }

    /**
     * 使用DSL查询
     *
     * @param flux DSL对象
     * @param clazz 查询结果POJO类
     * @return 查询结果
     * @param <T>
     */
    public <T> List<T> queryPointList(Flux flux, Class<T> clazz) {
        return queryApi.query(flux.toString(), org, clazz);
    }

    /**
     * 使用flux语句查询
     *
     * @param flux DSL对象
     * @param clazz 查询结果POJO类
     * @return 查询结果
     * @param <T>
     */
    public <T> List<T> queryPointList(String flux, Class<T> clazz) {
        return queryApi.query(flux, org, clazz);
    }
}
