<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="activeUser_bar" style="width: 600px;height: 400px;;margin-top: 30px;margin-left: 30px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('activeUser_bar'));


    // 异步加载统计信息
    $.post("${pageContext.request.contextPath }/user/selectAllUserByBar", function (data) {
        console.log(data);
        console.log(data.intervals);
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持名法州App用户活跃图'
            },
            tooltip: {},
            legend: {
                data: ['用户数量']
            },
            xAxis: {
                data: data.intervals
            },
            yAxis: {},
            series: [{
                name: '数量',
                type: 'bar',
                data: data.counts
            }]
        };

        myChart.setOption(option);
    }, "JSON");


</script>
