<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  #container{
    width: 500px;
    border: 2px solid red;
  }
</style>
<script>
  chart1 = {
    init:function () {
      this.getdata();

    },
    getdata:function (){
      $.ajax({
        url:'<c:url value="/chart1"/>',
        success:(data)=>{
          this.display(data);
        }
      })
    },
    display:function () {
      Highcharts.chart('container', {
        chart: {
          type: 'line'
        },
        title: {
          text: 'Monthly Average Temperature'
        },
        subtitle: {
          text: 'Source: ' +
                  '<a href="https://en.wikipedia.org/wiki/List_of_cities_by_average_temperature" ' +
                  'target="_blank">Wikipedia.com</a>'
        },
        xAxis: {
          categories: [
            'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep',
            'Oct', 'Nov', 'Dec'
          ]
        },
        yAxis: {
          title: {
            text: 'Temperature (°C)'
          }
        },
        plotOptions: {
          line: {
            dataLabels: {
              enabled: true
            },
            enableMouseTracking: false
          }
        },
        series: [{
          name: 'Korea',
          data: [
            16.0, 18.2, 23.1, 27.9, 32.2, 36.4, 39.8, 38.4, 35.5, 29.2,
            22.0, 17.8
          ]
        }, {
          name: 'Japan',
          data: [
            -2.9, -3.6, -0.6, 4.8, 10.2, 14.5, 17.6, 16.5, 12.0, 6.5,
            2.0, -0.9
          ]
        }]
      });
    }

  }
  $(function (){
    chart1.init();
  });

</script>

<div class="col-sm-7">
  <h2>Chart 1</h2>
  <div id="container"></div>
</div>
