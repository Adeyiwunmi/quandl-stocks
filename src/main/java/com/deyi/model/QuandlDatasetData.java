package com.deyi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ade on 1/14/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuandlDatasetData {

    private static final Logger logger = Logger.getLogger(QuandlDatasetData.class.getName());
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String limit;
    private String transform;
    private String column_index;
    private String[] column_names;
    private String start_date;
    private String end_date;
    private String frequency;
    private String[][] data;
    private String collapse;
    private String order;


    public static QuandlDatasetData fromJsonString(String jsonString) {

        if (StringUtils.isEmpty(jsonString)) {
            return new QuandlDatasetData();
        }
        try {
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

            return OBJECT_MAPPER.readValue(jsonString, QuandlDatasetData.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "unable to parse the response json", e);
            return new QuandlDatasetData();
        }
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getTransform() {
        return transform;
    }

    public void setTransform(String transform) {
        this.transform = transform;
    }

    public String getColumn_index() {
        return column_index;
    }

    public void setColumn_index(String column_index) {
        this.column_index = column_index;
    }

    public String[] getColumn_names() {
        return column_names;
    }

    public void setColumn_names(String[] column_names) {
        this.column_names = column_names;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getCollapse() {
        return collapse;
    }

    public void setCollapse(String collapse) {
        this.collapse = collapse;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public static final class Data {

        private String date;
        private double amount;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }


}
