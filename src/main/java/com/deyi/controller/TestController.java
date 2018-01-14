package com.deyi.controller;

import com.deyi.model.QuandlResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ade on 1/14/18.
 */
@RestController
@RequestMapping("/quadl-data")
public class TestController {

    private static final Logger logger = Logger.getLogger(TestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public QuandlResponse returnstuff() {
        String url = "https://www.quandl.com/api/v3/datasets/WIKI/FB/data.json?column_index=4&sort_order=asc&start_date=2016-04-18&end_date=2016-04-22&api_key=q9rY1mbd-5-Gxm47czBW";
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> data = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
            String responseJson = data.getBody();

            return QuandlResponse.fromJsonString(responseJson);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error calling service ", e);

            return new QuandlResponse();
        }
    }

}
