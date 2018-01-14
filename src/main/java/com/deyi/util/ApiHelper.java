package com.deyi.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ade on 1/14/18.
 */
public class ApiHelper {

    private static final Logger logger = Logger.getLogger(ApiHelper.class.getName());

    private ApiHelper() {

    }

    public static String exchange(String builtUrl) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(builtUrl, HttpMethod.GET, HttpEntity.EMPTY, String.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "There is no data found for the Quandl code", e);
            return "Not Found";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "There was an error calling the Quandl web service", e);
            return null;
        }
    }
}
