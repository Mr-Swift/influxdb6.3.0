package com.example.influxdb;

import com.example.influxdb.base.activate.QueryMeasurement;
import com.example.influxdb.base.activate.WriteMeasurement;
import com.example.influxdb.pojo.Equipment;
import com.influxdb.client.DeleteApi;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.*;

@SpringBootTest
class ApplicationTests {

    @Resource
    public InfluxDBClient influxDBClient;

    @Resource
    private WriteMeasurement writeMeasurement;


    @Resource
    private QueryMeasurement queryMeasurement;

    @Test
    void contextLoads() {
        DeleteApi deleteApi = influxDBClient.getDeleteApi();

        System.out.println(deleteApi);
    }


    @Test
    void writeApi(){
        Equipment equipment = new Equipment();
        equipment.setId(UUID.randomUUID().toString())
                .setName("设备1")
                .setCode("001")
                .setLocation("厨房")
                .setVoltage(1.5)
                .setElectricity(1.0)
                .setTime(Instant.now())
                .setMeasurement("equipment");

        List<Equipment> list = new ArrayList<>();
        list.add(equipment);
        writeMeasurement.writePoint("equipment_info", list);
    }

    @Test
    void queryTest(){
        Flux flux=Flux.from("equipment_info")
                .range(-8L, ChronoUnit.HOURS)
                .group()
                .last()
                .pivot()
                .withRowKey(new String[]{"_time"})
                .withColumnKey(new String[]{"_field"})
                .withValueColumn("_value");

        String fluxStr = "from(bucket: \"equipment_info\")\n" +
                "  |> range(start: 0)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"equipment\")\n" +
                "  |> filter(fn: (r) => r[\"_field\"] == \"electricity\" or r[\"_field\"] == \"voltage\")\n" +
                "  |> pivot(\n" +
                "    rowKey:[\"_time\"],\n" +
                "    columnKey: [\"_field\"],\n" +
                "    valueColumn: \"_value\"\n" +
                "  )\n";
        List<Equipment> list = queryMeasurement.queryPointList(fluxStr, Equipment.class);
        Optional.ofNullable(list).orElse(new ArrayList<>()).forEach(item->{
            System.out.println(item.toString());
        });
    }

}
