package com.deyi.controller;

import com.deyi.model.StockPriceResponseDTO;
import com.deyi.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ade on 1/14/18.
 */

@RestController
@RequestMapping("/stock-prices")
public class StockPricesController {

    @Autowired
    private StockPriceService stockPriceService;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
public StockPriceResponseDTO getStockForLastWeek(@PathVariable(value = "code")String quandlCode){
return stockPriceService.getStockPricesForPreviousWeek(quandlCode);
}
}
