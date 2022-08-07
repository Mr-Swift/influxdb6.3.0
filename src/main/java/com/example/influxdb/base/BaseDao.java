package com.example.influxdb.base;

import com.example.influxdb.base.activate.DeleteData;
import com.example.influxdb.config.InfluxDBConfig;
import com.example.influxdb.base.activate.QueryMeasurement;
import com.example.influxdb.base.activate.WriteMeasurement;
import com.influxdb.query.dsl.Flux;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.*;


@Repository
public class BaseDao {

    @Resource
    private WriteMeasurement writeMeasurement;

    @Resource
    private QueryMeasurement queryMeasurement;

    @Resource
    private DeleteData deleteData;

    @Resource
    private InfluxDBConfig influxDBConfig;

    /**
     * 批量写入
     *
     * @param bucketName 存储桶名称
     * @param list 待存储的集合
     * @param <T>
     */
    public <T> void setWriteMeasurement(String bucketName,List<T> list){
        try {
            writeMeasurement.writePoint(bucketName, list);
        }catch (Exception e) {
            e.printStackTrace();
            influxDBConfig.influxDBClient();
        }

    }

    /**
     * 使用DSL查询
     *
     * @param flux DSL对象
     * @param clazz 查询结果POJO类
     * @return
     * @param <T>
     */
    public <T> List<T> queryMeasurement(Flux flux, Class<T> clazz) {
        List<T> list=null;
        try{
            list = queryMeasurement.queryPointList(flux, clazz);
        }catch (Exception e) {
            e.printStackTrace();
            influxDBConfig.influxDBClient();
        }
        return list;
    }

    /**
     * 使用flux语句查询
     *
     * @param flux DSL对象
     * @param clazz 查询结果POJO类
     * @return
     * @param <T>
     */
    public <T> List<T> queryMeasurement(String flux, Class<T> clazz) {
        List<T> list=null;
        try{
            list = queryMeasurement.queryPointList(flux, clazz);
        }catch (Exception e) {
            e.printStackTrace();
            influxDBConfig.influxDBClient();
        }
        return list;
    }

    /**
     * 删除
     *
     * @param bucketName 存储桶名称
     * @param start 开始时间
     * @param stop 结束时间
     */
    public void delete(String bucketName, OffsetDateTime start, OffsetDateTime stop) {
        try {
            deleteData.delete(bucketName, start, stop);
        } catch (Exception e) {
            e.printStackTrace();
            influxDBConfig.influxDBClient();
        }
    }


    /**
     *
     * @param bucketName 存储桶名称
     * @param predicate 删除条件（key1="value1" AND key2="value2"）
     * @param start 开始时间
     * @param stop 结束时间
     */
    public void delete(String bucketName,String predicate, OffsetDateTime start, OffsetDateTime stop) {
        try {
            deleteData.delete(bucketName, predicate,start, stop);
        } catch (Exception e) {
            e.printStackTrace();
            influxDBConfig.influxDBClient();
        }
    }

}
