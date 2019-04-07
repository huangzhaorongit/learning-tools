package com.distribution.wamoli.modules.ts.mapper;

import com.distribution.wamoli.common.mapper.BaseMapper;
import com.distribution.wamoli.modules.ts.domain.TimeSeries;

import java.util.List;


/**
 * @author 方八零
 * @version 2016-06-5
 */
public interface TimeSeriesMapper extends BaseMapper<TimeSeries> {
	public TimeSeries updateByColName(TimeSeries timeSeries);
	public List<TimeSeries> findStatList(TimeSeries timeSeries);
}
