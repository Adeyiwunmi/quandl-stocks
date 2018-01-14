package com.deyi.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by ade on 1/14/18.
 */
public class UrlBuilder {

    private static final String BASE_URL = "https://www.quandl.com/api/v3/datasets/WIKI/";
    private static final String DATA_FORMAT = "/data.json?";
    private static final String API_DATA_FILTER = "column_index=4&sort_order=asc&";

    public static String buildUrl(String quandlCode, String startDate, String endDate, String api_key) {
        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        stringBuilder.append(quandlCode);
        stringBuilder.append(DATA_FORMAT);
        stringBuilder.append(API_DATA_FILTER);
        stringBuilder.append("start_date=");
        stringBuilder.append(startDate);
        stringBuilder.append("&end_date=");
        stringBuilder.append(endDate);
        stringBuilder.append("&api_key=");
        stringBuilder.append(api_key);
        return stringBuilder.toString();
    }
}
