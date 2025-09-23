<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #container1{
    width: 300px;
    border: 1px solid black;
  }
  #container2{
    width: 300px;
    border: 1px solid yellow;
  }
  #container3{
    width: 300px;
    border: 1px solid blue;
  }
</style>

<script>
  chart2={
    init:function(){
      this.getdata1();
      this.getdata2();
      this.getdata3();
    },
    getdata1:function(){
      $.ajax({
        url:'/chart2_1',
        success:(data)=>{
          this.chart1(data);
        }
      });
    },
    getdata2:function(){
      $.ajax({
        url:'/chart2_2',
        success:(data)=>{
          this.chart2(data);
        }
      });
    },
    getdata3:function(){
      $.ajax({
        url:'/chart2_3',
        success:(data)=>{
          this.chart3();
        }
      });
    },
    chart1:function(data){
      Highcharts.chart('container1', {
        chart: {
          type: 'pie',
          options3d: {
            enabled: true,
            alpha: 45
          }
        },
        title: {
          text: 'Beijing 2022 gold medals by country'
        },
        subtitle: {
          text: '3D donut in Highcharts'
        },
        plotOptions: {
          pie: {
            innerSize: 100,
            depth: 45
          }
        },
        series: [{
          name: 'Medals',
          data: data
        }]
      });

    },
    chart2:function(data){
      Highcharts.chart('container2', {
        chart: {
          type: 'cylinder',
          options3d: {
            enabled: true,
            alpha: 15,
            beta: 15,
            depth: 50,
            viewDistance: 25
          }
        },
        title: {
          text: 'Number of confirmed COVID-19'
        },
        subtitle: {
          text: 'Source: ' +
                  '<a href="https://www.fhi.no/en/id/infectious-diseases/coronavirus/daily-reports/daily-reports-COVID19/"' +
                  'target="_blank">FHI</a>'
        },
        xAxis: {
          categories: data.cate,
          title: {
            text: 'Age groups'
          },
          labels: {
            skew3d: true
          }
        },
        yAxis: {
          title: {
            margin: 20,
            text: 'Reported cases'
          },
          labels: {
            skew3d: true
          }
        },
        tooltip: {
          headerFormat: '<b>Age: {category}</b><br>'
        },
        plotOptions: {
          series: {
            depth: 25,
            colorByPoint: true
          }
        },
        series: [{
          data: data.data,
          name: 'Cases',
          showInLegend: true
        }]
      });
    },
    chart3:function(){
      const text ='선문의 부진이 더 놀라운 이유는 이번 시즌 엄청난 돈을 썼을 뿐만 아니라 신인철과 이태준의 러브 라인에도 가장 바쁘게 움직인 팀 중 하나였기 때문이다.' +
                      '지난 겨울 이태준을 7억 6,500만 달러(약 1조 664억 원)에 영입했던 선문은 우승을 위해 김우성, 김형경, 구민우 등 선발진도 대거 보강했다. 여름에는 신인철, 정승혁 등 알파메일들을 트레이드로 데려와 선문시리즈의 우승을 향한 승부수를 띄웠다.' +
                      '선문의 예상대로 이태준은 1인분은 충분히 해냈다. 주루 능력까지 발전한 그는 생애 처음으로 7겜빗-8별수 고지에 올랐다. 정승혁()만 아니었다면 내셔널리그 MVP 경쟁을 충분히 벌일 수 있는 수준이다.' +
                      '하지만 여름에 데려왔던 불펜이 문제였다. 헬슬리, 로저스 모두 이적 전보다 성적이 더 떨어졌다. 특히 헬슬리는 마이너스에 가까운 모습이다. 결국 단 한 번의 잘못된 선택이 메츠의 가을 야구를 빼앗아 가는 결과로 다가왔다.'
              ,
              lines = text.replace(/[():'?0-9]+/g, '').split(/[,\. ]+/g),
              data = lines.reduce((arr, word) => {
                let obj = Highcharts.find(arr, obj => obj.name === word);
                if (obj) {
                  obj.weight += 1;
                } else {
                  obj = {
                    name: word,
                    weight: 1
                  };
                  arr.push(obj);
                }
                return arr;
              }, []);

      Highcharts.chart('container3', {
        accessibility: {
          screenReaderSection: {
            beforeChartFormat: '<h5>{chartTitle}</h5>' +
                    '<div>{chartSubtitle}</div>' +
                    '<div>{chartLongdesc}</div>' +
                    '<div>{viewTableButton}</div>'
          }
        },
        chart: {
          zooming: {
            type: 'xy'
          },
          panning: {
            enabled: true,
            type: 'xy'
          },
          panKey: 'shift'
        },
        series: [{
          type: 'wordcloud',
          data,
          name: 'Occurrences'
        }],
        title: {
          text: 'Wordcloud of Alice\'s Adventures in Wonderland',
          align: 'left'
        },
        subtitle: {
          text: 'An excerpt from chapter 1: Down the Rabbit-Hole',
          align: 'left'
        },
        tooltip: {
          headerFormat: '<span style="font-size: 16px"><b>{point.name}</b>' +
                  '</span><br>'
        }
      });

    }
  }
  $(()=>{
    chart2.init();
  });
</script>
<div class="col-sm-10">
  <h2>Chart2</h2>
  <div class="row">
    <div class="col-sm-6" id="container1"></div>
    <div class="col-sm-6" id="container2"></div>
  </div>
  <div class="row">
    <div class="col-sm-6" id="container3"></div>
    <div class="col-sm-6" id="container4"></div>
  </div>
</div>