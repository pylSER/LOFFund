
$(function () {
    $.getJSON('getstockdata.jsp',{days:50},function (data){
    	

        // // set the allowed units for data grouping
        // var groupingUnits = [[
        //         'week',                         // unit name
        //         [1]                             // allowed multiples
        //     ], [
        //         'month',
        //         [1, 2, 3, 4, 6]
        //     ]]

        var kdata = [],
            predict = [],
            dataLength = data.length,

            i = 0;

        for (i; i < dataLength; i += 1) {
            kdata.push([
                data[i][0], // the date
                data[i][1], // the open
                data[i][2], // the high
                data[i][3], // the low
                data[i][4]  // the close
            ]);

            predict.push([
                data[i][0], // the date
                data[i][5]  // the predict
            ]);
        }

        // create the chart
        $('#container').highcharts('StockChart', {

            rangeSelector: {
                selected: 1
            },
            title: {
                text: '上证综指(000001)'  
            },
            tooltip: {
                valuePrefix: '¥ '
            },

            series: [{
                type: 'candlestick',
                name: '上证综指',
                data: kdata
                // dataGrouping: {
                //     units: groupingUnits
                // }
            },{
                name: '预测收盘价',
                color:'#DC5E5B',
                data: predict
            }]
        });
    });
});


