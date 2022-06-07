import {Component, OnInit} from '@angular/core';
import {StatisticalManagementService} from '../../../../service/admin/statistical-management.service';

@Component({
  selector: 'app-category-statistics',
  templateUrl: './category-statistics.component.html',
  styleUrls: ['./category-statistics.component.scss']
})
export class CategoryStatisticsComponent implements OnInit {

  basicData: any;
  basicOptions: any;
  categoryTop = 5;
  chartHide = false;

  // tslint:disable-next-line:variable-name
  constructor(private _statisticalService: StatisticalManagementService) {
  }

  ngOnInit(): void {
    this.onCategoryTop();
  }

  onCategoryTop() {
    this._statisticalService.getTopMovieCategory(this.categoryTop).subscribe(data => {
      this.initData();
      if (data != null) {
        for (const item of data) {
          const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0');
          this.basicData.labels.push(item.categoryName);
          this.basicData.datasets[0].backgroundColor.push(randomColor);
          this.basicData.datasets[0].data.push(item.ticketQuantity);
        }
        this.chartHide = false;
      } else {
        this.chartHide = true;
      }
    });
    this.initBasicOptions();
  }

  initData() {
    this.basicData = {
      labels: [],
      datasets: [
        {
          label: 'Số vé (Vé)',
          backgroundColor: [],
          data: [],
        }
      ]
    };
  }

  initBasicOptions() {
    this.basicOptions = {
      legend: {
        labels: {
          fontColor: '#495057'
        }
      },
      scales: {
        xAxes: [{
          ticks: {
            fontColor: '#495057',
          },
          barPercentage: 0.5
        }],
        yAxes: [{
          ticks: {
            min: 0,
            precision: 0,
            fontColor: '#495057'
          }
        }]
      }
    };
  }

}

