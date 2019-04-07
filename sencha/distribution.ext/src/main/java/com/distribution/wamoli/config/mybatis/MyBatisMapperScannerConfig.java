package com.distribution.wamoli.config.mybatis;

import com.distribution.wamoli.common.mapper.BaseMapper;
import com.distribution.wamoli.config.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by baling.fang 于 2017年01月08日.
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.distribution.wamoli.mapper, com.distribution.wamoli.modules.**.mapper");
        Properties properties = new Properties();
        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。
        properties.setProperty("mappers", BaseMapper.class.getName());
//        properties.setProperty("markerInterface", BaseMapper.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");

        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
