/**
 * Created by peiyulin on 16/9/2.
 */

$(function () {

	
	var code=document.getElementById("code").innerText;
	
    $.getJSON('backTest.jsp', {name:code}, function (data) {
    	
    	  var real = [],
          predict = [],
          dataLength = data.length,

          i = 0;

      for (i; i < dataLength; i += 1) {
          real.push([
              data[i][0], // the date
              data[i][1], // the real
          ]);

          predict.push([
              data[i][0], // the date
              data[i][2] // the predict
          ]);
      }

        // Create the chart   	
        $('#chartplace').highcharts('StockChart', {
        	 rangeSelector : {
                 selected : 1
             },
             tooltip: {
                 valuePrefix: '¥ '
             },
             series : [{
                 name : '真实价格',
                 data : real,
               //  tooltip: {
                //     valueDecimals: 4
                // }
             },
             {
            	 name:'预测价格',
            	 data : predict,
            	 color:'#DC5E5B',
                 tooltip: {
                     valueDecimals: 6
                 }
             }]                            
          });  
    });
});