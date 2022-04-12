<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<base href="${pageContext.request.contextPath}/SRI">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <script type="text/javascript" src="static/js/index/UserIndex.js"></script>
    <script src="static/js/jq/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="static/css/index/UserIndex.css">
</head>

<body>
    <div class="all">
        <div id="main" style="height: 100%"></div>
    </div>
    <script src="static/js/echars/echarts.min.js"></script>
  <script>
    var today = new Date();
    var todayYear = today.getFullYear();
    //alert(todayYear);
    var main = document.getElementById("main");
    var myChart = echarts.init(main);
    window.onresize = function() {
        myChart.resize();
    };
    var app = {};

    var option;


    const posList = [
      'left',
      'right',
      'top',
      'bottom',
      'inside',
      'insideTop',
      'insideLeft',
      'insideRight',
      'insideBottom',
      'insideTopLeft',
      'insideTopRight',
      'insideBottomLeft',
      'insideBottomRight'
    ];
    app.configParameters = {
      rotate: {
        min: -90,
        max: 90
      },
      align: {
        options: {
          left: 'left',
          center: 'center',
          right: 'right'
        }
      },
      verticalAlign: {
        options: {
          top: 'top',
          middle: 'middle',
          bottom: 'bottom'
        }
      },
      position: {
        options: posList.reduce(function (map, pos) {
          map[pos] = pos;
          return map;
        }, {})
      },
      distance: {
        min: 0,
        max: 100
      }
    };
    app.config = {
      rotate: 0,
      align: 'center',
      verticalAlign: 'middle',
      position: 'top',
      distance: 10,
      onChange: function () {
        const labelOption = {
          rotate: app.config.rotate,
          align: app.config.align,
          verticalAlign: app.config.verticalAlign,
          position: app.config.position,
          distance: app.config.distance
        };
        myChart.setOption({
          series: [
            {
              label: labelOption
            },
            {
              label: labelOption
            },
            {
              label: labelOption
            },
            {
              label: labelOption
            }
          ]
        });
      }
    };
    const labelOption = {
      show: true,
      position: app.config.position,
      distance: app.config.distance,
      align: app.config.align,
      verticalAlign: app.config.verticalAlign,
      rotate: app.config.rotate,
      formatter: '{c}',
      fontSize: 14
    };
    option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['立项', '横项', '论文', '著作', '专利', '成果获奖'],
        textStyle: {
          fontSize: 14
        }
      },
      toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ['line', 'bar', 'stack'] },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      xAxis: [
        {
          type: 'category',
          axisTick: { show: false },
          data: [todayYear-4, todayYear-3, todayYear-2, todayYear-1, todayYear],
          axisLabel: {
            textStyle: {
              fontSize:14,
            }
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          axisLabel: {
            textStyle: {
              fontSize:14,
            }
          }
        }
      ],
      series: [
        {
          name: '立项',
          type: 'bar',
          barGap: 0,
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${lxNum}
        },
        {
          name: '横项',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${hxNum}
        },
        {
          name: '论文',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${thesisNum}
        },
        {
          name: '著作',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${workNum}
        },
        {
          name: '专利',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${patentNum}
        },
        {
          name: '成果获奖',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: ${achievementNum}
        }
      ]
    };


    option && myChart.setOption(option);
  </script>
</body>

</html>