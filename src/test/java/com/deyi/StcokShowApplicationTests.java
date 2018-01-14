package com.deyi;

import com.deyi.service.StockPriceService;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StcokShowApplicationTests {

	private static final Logger logger = Logger.getLogger(StcokShowApplicationTests.class.getName());
	@Autowired
	private StockPriceService stockPriceService;
	@Test
	public void contextLoads() {
	}


	@Test
	public void testDate(){
		Pair<String , String> startAndEndDate = stockPriceService.getEndAndStartDate();
		logger.log(Level.SEVERE, startAndEndDate.getLeft());
		logger.log(Level.SEVERE, startAndEndDate.getRight());
	}
}
