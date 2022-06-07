import {Component, OnInit} from '@angular/core';
import {StatisticalManagementService} from '../../../../service/admin/statistical-management.service';
import * as moment from 'moment';

@Component({
  selector: 'app-movie-statistics',
  templateUrl: './movie-statistics.component.html',
  styleUrls: ['./movie-statistics.component.scss']
})
export class MovieStatisticsComponent implements OnInit {

  multiAxisData: any;
  multiAxisOptions: any;
  statBy = 3;
  startDate = '';
  endDate = '';
  month: number;
  year: number;
  curDate = new Date();
  chartHide = false;
  chartHideTop = false;
  dateValidate = false;
  multiAxisDataTop: any;
  multiAxisOptionsTop: any;
  movieTop = 5;

  constructor(private statisticalService: StatisticalManagementService) {
  }

  ngOnInit(): void {
    this.startDate = moment(this.curDate).format('yyyy-MM-DD');
    this.endDate = moment(this.curDate).format('yyyy-MM-DD');
    this.month = this.curDate.getMonth() + 1;
    this.year = this.curDate.getFullYear();
    this.onStatByYear();
    this.onMovieTop();
  }

  onStatByDate() {
    if (this.startDate > this.endDate) {
      this.dateValidate = true;
    } else {
      this.dateValidate = false;

      this.statisticalService.getMovieStatisticsByDate(this.startDate, this.endDate).subscribe(data => {
        this.initData();
        if (data != null) {
          this.multiAxisData.labels.push(0);
          this.multiAxisData.datasets[0].data.push(0);
          this.multiAxisData.datasets[1].data.push(0);
          for (const item of data) {
            this.multiAxisData.labels.push(moment(item.createDate).format('DD/MM/yyyy'));
            this.multiAxisData.datasets[0].data.push(item.revenue);
            this.multiAxisData.datasets[1].data.push(item.ticketQuantity);
          }
          this.chartHide = false;
        } else {
          this.chartHide = true;
        }
      });
      this.initMultiAxisOptions();
    }
  }

  onStatByMonth() {
    this.statisticalService.getMovieStatisticsByMonth(this.month, this.year).subscribe(data => {
      this.initData();
      if (data != null) {
        for (let i = 1; i <= this.checkMonth(this.month, this.year); i++) {
          this.multiAxisData.labels.push(i);
          this.multiAxisData.datasets[0].data.push(0);
          this.multiAxisData.datasets[1].data.push(0);
          // tslint:disable-next-line:prefer-for-of
          for (let j = 0; j < data.length; j++) {
            // tslint:disable-next-line:radix
            if (parseInt(data[j].createDate.substring(8, 10)) === i) {
              this.multiAxisData.datasets[0].data[i - 1] = data[j].revenue;
              this.multiAxisData.datasets[1].data[i - 1] = data[j].ticketQuantity;
            }
          }
        }
        this.chartHide = false;
      } else {
        this.chartHide = true;
      }
    });
    this.initMultiAxisOptions();
  }

  onStatByYear() {
    this.statisticalService.getMovieStatisticsByYear(this.year).subscribe(data => {
      this.initData();
      if (data != null) {
        for (let i = 1; i <= 12; i++) {
          this.multiAxisData.labels.push(i);
          this.multiAxisData.datasets[0].data.push(0);
          this.multiAxisData.datasets[1].data.push(0);
          // tslint:disable-next-line:prefer-for-of
          for (let j = 0; j < data.length; j++) {
            // tslint:disable-next-line:radix
            if (parseInt(data[j].createDate.substring(5, 7)) === i) {
              this.multiAxisData.datasets[0].data[i - 1] = data[j].revenue;
              this.multiAxisData.datasets[1].data[i - 1] = data[j].ticketQuantity;
            }
          }
        }
        this.chartHide = false;
      } else {
        this.chartHide = true;
      }
    });
    this.initMultiAxisOptions();
  }

  onMovieTop() {
    this.statisticalService.getTopMovie(this.movieTop).subscribe(data => {
      this.initDataTop();
      if (data != null) {
        for (const item of data) {
          this.multiAxisDataTop.labels.push(item.movieName);
          this.multiAxisDataTop.datasets[0].data.push(item.ticketQuantity);
          this.multiAxisDataTop.datasets[1].data.push(item.revenue);
        }
        this.chartHideTop = false;
      } else {
        this.chartHideTop = true;
      }
    });
    this.initMultiAxisOptionsTop();
  }

  initData() {
    this.multiAxisData = {
      labels: [],
      datasets: [{
        label: 'Doanh thu (VND)',
        fill: false,
        borderColor: '#66BB6A',
        yAxisID: 'y-axis-1',
        data: []
      }, {
        label: 'Số vé (Vé)',
        fill: false,
        borderColor: '#FFA726',
        yAxisID: 'y-axis-2',
        data: []
      }]
    };
  }

  initDataTop() {
    this.multiAxisDataTop = {
      labels: [],
      datasets: [{
        label: 'Số vé (Vé)',
        backgroundColor: '#42A5F5',
        yAxisID: 'y-axis-1',
        data: []
      }, {
        label: 'Doanh thu (VND)',
        backgroundColor: '#FFA726',
        yAxisID: 'y-axis-2',
        data: []
      }]
    };
  }

  initMultiAxisOptions() {
    this.multiAxisOptions = {
      responsive: true,
      hoverMode: 'index',
      stacked: false,
      scales: {
        yAxes: [{
          type: 'linear',
          display: true,
          position: 'left',
          id: 'y-axis-1',
          ticks: {
            min: 0,
            precision: 0,
            callback(value, index, values) {
              return value.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
            }
          }
        }, {
          type: 'linear',
          display: true,
          position: 'right',
          id: 'y-axis-2',
          gridLines: {
            drawOnChartArea: false
          },
          ticks: {
            min: 0,
            precision: 0
          }
        }]
      }
    };
  }

  initMultiAxisOptionsTop() {
    this.multiAxisOptionsTop = {
      responsive: true,
      tooltips: {
        mode: 'index',
        intersect: true
      },
      scales: {
        yAxes: [{
          type: 'linear',
          display: true,
          position: 'left',
          id: 'y-axis-1',
          ticks: {
            min: 0,
            precision: 0
          }
        },
          {
            type: 'linear',
            display: true,
            position: 'right',
            id: 'y-axis-2',
            gridLines: {
              drawOnChartArea: false
            },
            ticks: {
              min: 0,
              precision: 0,
              callback(value, index, values) {
                return value.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
              }
            }
          }]
      }
    };
  }

  resetValidate() {
    this.dateValidate = false;
  }

  checkMonth(month: number, year: number) {
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return 30;
      case 2:
        if (this.checkLeapYear(year)) {
          return 29;
        }
        return 28;

    }
  }

  checkLeapYear(year: number) {
    if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
      return true;
    }
    return false;
  }

}
