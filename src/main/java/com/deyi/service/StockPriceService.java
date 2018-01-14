package com.deyi.service;

import com.deyi.model.QuandlDatasetData;
import com.deyi.model.QuandlResponse;
import com.deyi.model.StockPriceResponseDTO;
import com.deyi.util.ApiHelper;
import com.deyi.util.UrlBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ade on 1/14/18.
 */
@Service
public class StockPriceService {
    private static final Logger logger = Logger.getLogger(StockPriceService.class.getName());


    @Value("${quandl.api.key}")
    private String api_key;

    public StockPriceResponseDTO getStockPricesForPreviousWeek(String quandlCode) {
        Pair<String, String> startAndEndDate = getEndAndStartDate();
        String url = UrlBuilder.buildUrl(quandlCode, startAndEndDate.getLeft(), startAndEndDate.getRight(), api_key);
        String response = ApiHelper.exchange(url);
        try {
            return processResponse(response);
        } catch (JSONException e) {
            logger.log(Level.SEVERE, "There was an error parsing the response from Quandl", e);
            return StockPriceResponseDTO.fromSucceesAndMessage(false, "There was an error processing the data");
        }
    }

    public Pair<String, String> getEndAndStartDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        Date todayDate = calendar.getTime();
        String today = dateFormat.format(todayDate);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date last7DaysDate = calendar.getTime();
        String last7Days = dateFormat.format(last7DaysDate);
        return new ImmutablePair<>(last7Days, today);
    }

    public StockPriceResponseDTO processResponse(String response) throws JSONException {

        if (response == "Not Found") {
            StockPriceResponseDTO responseDTO = new StockPriceResponseDTO();
            responseDTO.setMessage("There is not stock found for code");
            responseDTO.setSuccess(false);
            responseDTO.setData(new String[][]{});
            return responseDTO;
        }

        if (response == null) {
            return StockPriceResponseDTO.fromSucceesAndMessage(false, "There was an error getting the ");
        }
        JSONObject responseJSON = new JSONObject(response);
        StockPriceResponseDTO responseDTO = new StockPriceResponseDTO();
        if (responseJSON.has("dataset_data")) {
            responseDTO.setMessage("Success");
            responseDTO.setSuccess(true);
            responseDTO.setData(getDataFromResponseJson(response));

        } else {
            responseDTO.setData(new String[0][0]);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("unknown error occurred");
        }
        return responseDTO;
    }

    private String[][] getDataFromResponseJson(String responseJson) {
        QuandlResponse quadlResponse = QuandlResponse.fromJsonString(responseJson);
        if (quadlResponse == null) {
            return null;
        }
        QuandlDatasetData quadlDatasetData = quadlResponse.getDataset_data();
        if (quadlDatasetData == null) {
            return null;
        }
        return quadlDatasetData.getData();
    }
}
