<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">

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
    <div class="main_box">
        <div id="main1"></div>
    </div>
    <div class="main_box">
        <div id="main2" style="padding: 0"></div>
        <%--        <div id="main2"></div>--%>
    </div>
    <div class="main_box">
        <div id="main3"></div>
    </div>
    <div class="main_box">
        <div id="main4"></div>
    </div>


</div>
<script src="static/js/echars/echarts.min.js"></script>
<script>
    var today = new Date();
    var todayYear = today.getFullYear();

    var main1 = document.getElementById("main1");
    var main2 = document.getElementById("main2");
    var main3 = document.getElementById("main3");
    var main4 = document.getElementById("main4");

    var myChart1 = echarts.init(main1);
    // var myChart2 = echarts.init(main2, 'dark');
    var myChart2 = echarts.init(main2);
    var myChart3 = echarts.init(main3);
    var myChart4 = echarts.init(main4);

    window.onresize = function () {
        myChart1.resize();
        myChart2.resize();
        myChart3.resize();
        myChart4.resize();
    };

    var app1 = {};

    var option1;
    var option2;
    var option3;
    var option4;

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
    app1.configParameters = {
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
    app1.config = {
        rotate: 0,
        align: 'center',
        verticalAlign: 'middle',
        position: 'top',
        distance: 10,
        onChange: function () {
            const labelOption = {
                rotate: app1.config.rotate,
                align: app1.config.align,
                verticalAlign: app1.config.verticalAlign,
                position: app1.config.position,
                distance: app1.config.distance
            };
            myChart1.setOption({
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
        position: app1.config.position,
        distance: app1.config.distance,
        align: app1.config.align,
        verticalAlign: app1.config.verticalAlign,
        rotate: app1.config.rotate,
        formatter: '{c}',
        fontSize: 14
    };
    const itemStyleOption = {
        borderRadius: [4, 4, 0, 0]
    };

    option1 = {
        title: {
            text: '数量分布',
            left: 'center',
            top: 5
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            bottom: '20',
            data: ['立项', '横项', '论文', '著作', '专利', '成果获奖'],
            textStyle: {
                fontSize: 12
            }
        },
        toolbox: {
            show: true,
            // orient: 'vertical',
            right: '20px',
            // top: 'center',
            top: 5,
            feature: {
                mark: {show: true},
                // dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: [
            {
                type: 'category',
                data: [todayYear - 4, todayYear - 3, todayYear - 2, todayYear - 1, todayYear],
                name: "年份",
                nameTextStyle: {
                    fontSize: 14
                },
                axisLabel: {
                    textStyle: {
                        fontSize: 14,
                    }
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                // type: 'value',
                name: "数量",
                nameTextStyle: {
                    fontSize: 14
                },
                axisLabel: {
                    textStyle: {
                        fontSize: 14,
                    }
                }
            }
        ],
        series: [
            {
                name: '立项',
                type: 'bar',
                smooth: true,
                barGap: 0,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${lxNum},
                itemStyle: itemStyleOption
            },
            {
                name: '横项',
                type: 'bar',
                smooth: true,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${hxNum},
                itemStyle: itemStyleOption
            },
            {
                name: '论文',
                type: 'bar',
                smooth: true,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${thesisNum},
                itemStyle: itemStyleOption
            },
            {
                name: '著作',
                type: 'bar',
                smooth: true,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${workNum},
                itemStyle: itemStyleOption
            },
            {
                name: '专利',
                type: 'bar',
                smooth: true,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${patentNum},
                itemStyle: itemStyleOption
            },
            {
                name: '成果获奖',
                type: 'bar',
                smooth: true,
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: ${achievementNum},
                itemStyle: itemStyleOption
            }
        ]
    };

    const dataValue2 = [9, 8, 12, 11, 5];
    const dataName2 = ['小刘', '小王', '小黎', '小明', '小红'];
    const redPie = ['#611b19', '#842421', '#a22c28', '#b9332e', '#d76662'];
    const bluePie = ['#1f3879', '#264698', '#2d54b4', '#325dc8', '#6283d7'];
    option2 = {

        title: {
            text: '项目数量排名',
            left: 'center',
            top: 5
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} 个'
            // formatter: '{b} : {c} 个 ({d}%)'
        },

        series: [
            {
                name: 'Access From',
                type: 'pie',
                // color: bluePie,
                radius: '65%',
                center: ['50%', '50%'],
                label: {
                    textStyle: {
                        fontSize: 16
                    }
                },
                data: [
                    {value: dataValue2[0], name: dataName2[0]},
                    {value: dataValue2[1], name: dataName2[1]},
                    {value: dataValue2[2], name: dataName2[2]},
                    {value: dataValue2[3], name: dataName2[3]},
                    {value: dataValue2[4], name: dataName2[4]}
                ].sort(function (a, b) {
                    return a.value - b.value;
                }),
                roseType: 'radius',
                labelLine: {
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                },
            }
        ]

    };

    option3 = {
        title: {
            text: '项目总数',
            left: 'center',
            top: 5
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        toolbox: {
            right: '20px',
            top: 5,
            feature: {
                mark: {show: true},
                // dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        grid: {
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                name: "年份",
                nameTextStyle: {
                    fontSize: 14
                },
                boundaryGap: false,
                data: [todayYear - 4, todayYear - 3, todayYear - 2, todayYear - 1, todayYear]
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: "数量",
                nameTextStyle: {
                    fontSize: 14
                },
            }
        ],
        series: [
            {
                name: '项目总数',
                type: 'line',
                stack: 'Total',
                // areaStyle: {},
                label: labelOption,
                smooth: true,
                emphasis: {
                    focus: 'series'
                },
                itemStyle: {
                    borderRadius: [10, 10, 0, 0]
                },
                data: ${totalNumList}
                // data: [23, 22, 27, 19, 28]
            }
        ]
    };


    option4 = {
        title: {
            text: '类别分布',
            left: 'center',
            top: 5
        },
        legend: {
            top: 'bottom'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b} : {c} 人 ({d}%)'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '65%'],
                selectedMode: 'single',
                avoidLabelOverlap: true,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                data: [
                    {value: ${lxSum}, name: '立项'},
                    {value: ${hxSum}, name: '横项'},
                    {value: ${thesisSum}, name: '论文'},
                    {value: ${workSum}, name: '著作'},
                    {value: ${patentSum}, name: '专利'},
                    {value: ${achievementSum}, name: '成果获奖'}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }

    option1 && myChart1.setOption(option1);
    option2 && myChart2.setOption(option2);
    option3 && myChart3.setOption(option3);
    option4 && myChart4.setOption(option4);
</script>
</body>

</html>