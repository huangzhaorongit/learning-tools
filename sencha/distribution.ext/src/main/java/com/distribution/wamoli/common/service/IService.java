/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.distribution.wamoli.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.utils.SqlMapper.SqlMapper;

import tk.mybatis.mapper.common.Mapper;


/**
 * 通用接口
 */
@Service
public interface IService<T> {

	//查询实体
    T selectByKey(Object key);
    //保存
    int save(T entity);
    //删除
    int delete(Object key);
    //更新全部
    int updateAll(T entity);
    //部分更新
    int updateNotNull(T entity);
    //条件查询
    List<T> selectByExample(Object example);

    //获取最大值
    double selectMax(String tableName, String columName, String condition);
    
    public SqlMapper getSqlMapper();
    
    //批量删除
	public <T> int batchDeleteByPrimaryKey(Mapper<T> mapper, List<T> listT);
	
	//批量更新主键
	public <T> int batchUpdateByPrimaryKeySelective(Mapper<T> mapper, List<T> listT) ;
	
	
	//批量插入
	public <T> int batchInsertSelective(Mapper<T> mapper, List<T> listT);

}
