package com.deyi.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ade on 1/14/18.
 */
public class QuandlResponse {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(QuandlResponse.class.getName());



    private QuandlDatasetData dataset_data;


    public static QuandlResponse fromJsonString(String jsonString){
        if (StringUtils.isEmpty(jsonString)) {
            return new QuandlResponse();
        }
        try {
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

            QuandlResponse quadlResponse= OBJECT_MAPPER.readValue(jsonString, QuandlResponse.class);
            return quadlResponse;
        } catch (JsonMappingException e){
            logger.log(Level.SEVERE, "unable to parse the response json", e);
            return new QuandlResponse();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "unable to parse the response json", e);
            return new QuandlResponse();
        }
    }
    public QuandlDatasetData getDataset_data() {
        return dataset_data;
    }

    public void setDataset_data(QuandlDatasetData dataset_data) {
        this.dataset_data = dataset_data;
    }
}
