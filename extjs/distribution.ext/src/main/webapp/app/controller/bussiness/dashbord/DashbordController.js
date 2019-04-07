Ext.define('app.controller.bussiness.dashbord.DashbordController', {
    extend: 'Ext.app.ViewController',
    alias:'controller.dashbord',
    afterRenderCharts : function() {

    	
    	 var me = this;
    	 
    	 
    	// document.getElementById("echarts-line-chart").style.width="600px";
    	 
		var e = echarts.init(document.getElementById("echarts-line-chart")), a = {
			title : {
				text : "未来一周气温变化"
			},
			tooltip : {
				trigger : "axis"
			},
			legend : {
				data : ["最高气温", "最低气温"]
			},
			grid : {
				x : 40,
				x2 : 40,
				y2 : 24
			},
			calculable : !0,
			xAxis : [{
						type : "category",
						boundaryGap : !1,
						data : ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
					}],
			yAxis : [{
						type : "value",
						axisLabel : {
							formatter : "{value} °C"
						}
					}],
			series : [{
						name : "最高气温",
						type : "line",
						data : [11, 11, 15, 13, 12, 13, 10],
						markPoint : {
							data : [{
										type : "max",
										name : "最大值"
									}, {
										type : "min",
										name : "最小值"
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}, {
						name : "最低气温",
						type : "line",
						data : [1, -2, 2, 5, 3, 2, 0],
						markPoint : {
							data : [{
										name : "周最低",
										value : -2,
										xAxis : 1,
										yAxis : -1.5
									}]
						},
						markLine : {
							data : [{
										type : "average",
										name : "平均值"
									}]
						}
					}]
		};
		e.setOption(a), $(window).resize(e.resize);
		
		
		
		var M = echarts.init(document.getElementById("echarts-gauge-chart")), x = {
		tooltip : {
			formatter : "{a} <br/>{c} {b}"
		},
		toolbox : {
			show : !0,
			feature : {
				mark : {
					show : !0
				},
				restore : {
					show : !0
				},
				saveAsImage : {
					show : !0
				}
			}
		},
		series : [{
					name : "速度",
					type : "gauge",
					min : 0,
					max : 220,
					splitNumber : 11,
					axisLine : {
						lineStyle : {
							width : 10
						}
					},
					axisTick : {
						length : 15,
						lineStyle : {
							color : "auto"
						}
					},
					splitLine : {
						length : 20,
						lineStyle : {
							color : "auto"
						}
					},
					title : {
						textStyle : {
							fontWeight : "bolder",
							fontSize : 20,
							fontStyle : "italic"
						}
					},
					detail : {
						textStyle : {
							fontWeight : "bolder"
						}
					},
					data : [{
								value : 40,
								name : "km/h"
							}]
				}, {
					name : "转速",
					type : "gauge",
					center : ["25%", "55%"],
					radius : "50%",
					min : 0,
					max : 7,
					endAngle : 45,
					splitNumber : 7,
					axisLine : {
						lineStyle : {
							width : 8
						}
					},
					axisTick : {
						length : 12,
						lineStyle : {
							color : "auto"
						}
					},
					splitLine : {
						length : 20,
						lineStyle : {
							color : "auto"
						}
					},
					pointer : {
						width : 5
					},
					title : {
						offsetCenter : [0, "-30%"]
					},
					detail : {
						textStyle : {
							fontWeight : "bolder"
						}
					},
					data : [{
								value : 1.5,
								name : "x1000 r/min"
							}]
				}, {
					name : "油表",
					type : "gauge",
					center : ["75%", "50%"],
					radius : "50%",
					min : 0,
					max : 2,
					startAngle : 135,
					endAngle : 45,
					splitNumber : 2,
					axisLine : {
						lineStyle : {
							color : [[.2, "#ff4500"], [.8, "#48b"],
									[1, "#228b22"]],
							width : 8
						}
					},
					axisTick : {
						splitNumber : 5,
						length : 10,
						lineStyle : {
							color : "auto"
						}
					},
					axisLabel : {
						formatter : function(e) {
							switch (e + "") {
								case "0" :
									return "E";
								case "1" :
									return "Gas";
								case "2" :
									return "F"
							}
						}
					},
					splitLine : {
						length : 15,
						lineStyle : {
							color : "auto"
						}
					},
					pointer : {
						width : 2
					},
					title : {
						show : !1
					},
					detail : {
						show : !1
					},
					data : [{
								value : .5,
								name : "gas"
							}]
				}, {
					name : "水表",
					type : "gauge",
					center : ["75%", "50%"],
					radius : "50%",
					min : 0,
					max : 2,
					startAngle : 315,
					endAngle : 225,
					splitNumber : 2,
					axisLine : {
						lineStyle : {
							color : [[.2, "#ff4500"], [.8, "#48b"],
									[1, "#228b22"]],
							width : 8
						}
					},
					axisTick : {
						show : !1
					},
					axisLabel : {
						formatter : function(e) {
							switch (e + "") {
								case "0" :
									return "H";
								case "1" :
									return "Water";
								case "2" :
									return "C"
							}
						}
					},
					splitLine : {
						length : 15,
						lineStyle : {
							color : "auto"
						}
					},
					pointer : {
						width : 2
					},
					title : {
						show : !1
					},
					detail : {
						show : !1
					},
					data : [{
								value : .5,
								name : "gas"
							}]
				}]
	};
	M.setOption(x), $(window).resize(M.resize);
	var w = echarts.init(document.getElementById("echarts-funnel-chart")), b = {
		title : {
			text : "漏斗图",
			subtext : "纯属虚构"
		},
		tooltip : {
			trigger : "item",
			formatter : "{a} <br/>{b} : {c}%"
		},
		legend : {
			data : ["展现", "点击", "访问", "咨询", "订单"]
		},
		calculable : !0,
		series : [{
					name : "漏斗图",
					type : "funnel",
					width : "40%",
					data : [{
								value : 60,
								name : "访问"
							}, {
								value : 40,
								name : "咨询"
							}, {
								value : 20,
								name : "订单"
							}, {
								value : 80,
								name : "点击"
							}, {
								value : 100,
								name : "展现"
							}]
				}, {
					name : "金字塔",
					type : "funnel",
					x : "50%",
					sort : "ascending",
					itemStyle : {
						normal : {
							label : {
								position : "left"
							}
						}
					},
					data : [{
								value : 60,
								name : "访问"
							}, {
								value : 40,
								name : "咨询"
							}, {
								value : 20,
								name : "订单"
							}, {
								value : 80,
								name : "点击"
							}, {
								value : 100,
								name : "展现"
							}]
				}]
	};
	w.setOption(b);
	//w.resize;
	$(window).resize(w.resize);
	
	

	}
});