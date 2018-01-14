package com.deyi.model;

/**
 * Created by ade on 1/14/18.
 */
public class StockPriceResponseDTO {
    private String message;
    private String[][] dataset;
    private boolean success;


    public  static StockPriceResponseDTO fromSucceesAndMessage(boolean success, String message){
        StockPriceResponseDTO responseDTO = new StockPriceResponseDTO();
        responseDTO.setMessage(message);
        responseDTO.setSuccess(false);
        return responseDTO;
    }
    public String[][] getDataset() {
        return dataset;
    }

    public void setData(String[][] data) {
        this.dataset = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
