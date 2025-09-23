<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  #result{
    width: 400px;
    border: 2px solid red;
  }
</style>
<script>
  let wt1 ={
    init:function (){
      $('#get_btn').click(()=>{
        let loc = $('#loc').val();
        this.getData(loc);
      });
    },
    getData:function (loc){
      $.ajax({
        url:'<c:url value="/getwt1"/>',
        data:{'loc':loc},
        success:(data)=>{
          // alert(data);
          console.log(data);
          this.display(data);
        }
      });
    },
    display:function (data){
      let txt = data.response.body.items.item[0].wfSv;
      $('#result').html(txt);
    }
  }
  $(function (){
    wt1.init();
  });
</script>

<div class="col-sm-10">
  <h2>Weather 1 Page</h2>
  <select id="loc">
    <option value="108">전국</option>
    <option value="109">서울 인천 경기</option>
    <option value="131">충청</option>
  </select>
  <button id="get_btn">get</button>
  <h5 id="status"></h5>
  <div id="result"></div>
</div>




<%--  9bbb911d710cd5b7508df0586b705a29  --%>
<%--  XllrXZQ8bIT1BrFFEBwSkBrCyRIRPQatx7YhVQSFsHC6bYlBh%2Fh3%2F2t4UGvooZvxtjMqmpVs5QuXiOH%2BKx5dVQ%3D%3D  --%>