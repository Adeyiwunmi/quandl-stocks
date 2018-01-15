import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { StockDataResponse } from '../model/StockDataResponse';
import * as moment from 'moment';
import { CurrencyPipe } from '@angular/common';


@Component({
  selector: 'app-stocksview',
  templateUrl: './stocksview.component.html',
  styleUrls: ['./stocksview.component.css']
})
export class StocksviewComponent implements OnInit {

  readonly LAST_ITEM_KEY: string = "lastQuery";
  stockPriceData: String[][];
  searchQuery: string;
  validResult: boolean;
  noDataFound: boolean;
  baseUrl: String = "http://localhost:8080/stock-prices/";

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.searchQuery = localStorage.getItem(this.LAST_ITEM_KEY);
    this.validResult = false;
    this.noDataFound = false;
  }

  getStockValues(): void {
    localStorage.setItem(this.LAST_ITEM_KEY, this.searchQuery);
    this.http.get<StockDataResponse>(this.baseUrl.concat(this.searchQuery)).subscribe(data => {
      this.stockPriceData = data.dataset;
      if (this.stockPriceData.length > 0) {
        this.validResult = true;
        this.noDataFound = false;
      } else {
        this.validResult = false;
        this.noDataFound = true;
      }
    }, (err: HttpErrorResponse) => {
      if (err.error instanceof Error) {
        console.log('Client-side error occured.');
      } else {
        console.log('Server-side error occured.');
      }
    });
  }


  getTimeDisplay(time: string): String {
    return moment(time).fromNow();
  }
}
