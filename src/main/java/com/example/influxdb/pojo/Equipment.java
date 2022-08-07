package com.example.influxdb.pojo;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import retrofit2.http.Tag;

import java.time.Instant;

@Measurement(name="equipment")
public class Equipment {

    @Column(measurement = true)
    private String measurement;

    //设备id
    @Column(tag = true)
    private String id;

    //设备编号
    @Column(tag = true)
    private String code;

    //设备名称
    @Column(tag = true)
    private String name;

    //安装地址
    @Column(tag = true)
    private String location;

    //电压
    @Column
    private Double voltage;

    //电流
    @Column
    private Double electricity;

    //时间
    @Column(timestamp = true)
    private Instant time;

    public String getId() {
        return id;
    }

    public Equipment setId(String id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Equipment setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Equipment setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Equipment setLocation(String location) {
        this.location = location;
        return this;
    }

    public Double getVoltage() {
        return voltage;
    }

    public Equipment setVoltage(Double voltage) {
        this.voltage = voltage;
        return this;
    }

    public Double getElectricity() {
        return electricity;
    }

    public Equipment setElectricity(Double electricity) {
        this.electricity = electricity;
        return this;
    }

    public Instant getTime() {
        return time;
    }

    public Equipment setTime(Instant time) {
        this.time = time;
        return this;
    }

    public String getMeasurement() {
        return measurement;
    }

    public Equipment setMeasurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", voltage=" + voltage +
                ", electricity=" + electricity +
                ", time=" + time +
                '}';
    }
}
