package com.example.influxdb.config.utils;

import com.influxdb.query.dsl.Flux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FluxUtils {
    /**
     * 自动补全flux语句的bucketName
     *
     * @param flux flux语句
     * @param bucketName bucket名称
     * @return 补全bucketName的flux语句
     */
    public static String bucketNameAutoComplete(String flux, String bucketName) {
        String result=null;
        //正则表达式
        String reg="from\\(bucket:\\\"((\\w*)|(\\s*))?\\\"\\)";
        //预编译
        Pattern pattern = Pattern.compile(reg);
        //进行匹配
        Matcher matcher = pattern.matcher(flux);
        if (matcher.find()) {
            result = matcher.replaceFirst("from(bucket:\"" + bucketName + "\")");
        }

        return result;
    }

    /**
     * 自动补全flux语句的bucketName
     *
     * @param flux flux语句
     * @param bucketName bucket名称
     * @return 补全bucketName的flux语句
     */
    public static String bucketNameAutoComplete(Flux flux, String bucketName) {
        String result=null;
        //正则表达式
        String reg="from\\(bucket:\\\"((\\w*)|(\\s*))?\\\"\\)";
        //预编译
        Pattern pattern = Pattern.compile(reg);
        //进行匹配
        Matcher matcher = pattern.matcher(flux.toString());
        if (matcher.find()) {
            result = matcher.replaceFirst("from(bucket:\"" + bucketName + "\")");
        }

        return result;
    }

}
