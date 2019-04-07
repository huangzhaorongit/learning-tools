package com.distribution.wamoli.modules.ts.service;

import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.modules.ts.domain.TimeSeries;
import com.distribution.wamoli.modules.utils.TimeSeriesUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 方八零
 * @version 2016-06-5
 */
@Service
@Transactional(readOnly = true)
public class TimeSeriesService extends BaseService<TimeSeries> {

//	public TimeSeries get(String id) {
//		return get(id);
//	}
//
//	public List<TimeSeries> findList(TimeSeries timeSeries) {
//		return findList(timeSeries);
//	}
//
//	public List<TimeSeries> findStatList(TimeSeries timeSeries) {
//		return findStatList(timeSeries);
//	}
//
//	public Page<TimeSeries> findPage(Page<TimeSeries> page, TimeSeries timeSeries) {
//		return findPage(page, timeSeries);
//	}
//
	@Transactional(readOnly = false)
	public void delete(TimeSeries timeSeries) {
		super.delete(timeSeries);
	}

	private String[] getCols(int c){
		if(c == 96)
			return TimeSeriesUtils.CURVE_96COLUMN;
		else if(c == 48)
			return TimeSeriesUtils.CURVE_48COLUMN;
		else if(c == 24)
			return TimeSeriesUtils.CURVE_24COLUMN;
		else
			return TimeSeriesUtils.CURVE_288COLUMN;
	}

//	public GsonOption getChartData(TimeSeries timeSeries){
//		List<TimeSeries> list = this.findList(timeSeries);
//		GsonOption option = EChartUtils.createEChart(timeSeries.getColNum());
//        List<Series> series = new ArrayList<Series>();
//		List<String> legend = new ArrayList<String>();
//		String[] cols = getCols(timeSeries.getColNum());
//		for(TimeSeries l : list){
//			Line li = new Line();
//			if(timeSeries.getCat() == null || timeSeries.getCat() == -1){
//				legend.add(l.getYmd() + "-" + DictUtils.getDictLabel(l.getCat().toString(), timeSeries.getMeasureType(), ""));
//				li.setName(l.getYmd() + "-" + DictUtils.getDictLabel(l.getCat().toString(), timeSeries.getMeasureType(), ""));
//			}else{
//				legend.add(l.getYmd());
//				li.setName(l.getYmd());
//			}
//			li.setName(l.getYmd());
//			li.smooth(true);
//			li.setType(SeriesType.line);
//			List<String> ld = new ArrayList<String>();
//			for(int i = 0; i < cols.length; i++){
//				try {
//					String val = BeanUtils.getProperty(l, cols[i]);
//					if(val != null && !"".equals(val)){
//						ld.add(val);
//					}else{
//						ld.add(null);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			li.setData(ld);
//			series.add(li);
//		}
//		option.series(series);
//		option.legend(legend.toArray());
//		return option;
//	}

}
