package com.distribution.wamoli.config;

import com.distribution.wamoli.config.mybatis.SpringBootVFS;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Mybatis. Contributes a
 * {@link SqlSessionFactory} and a {@link SqlSessionTemplate}.
 *
 * If {@link org.mybatis.spring.annotation.MapperScan} is used, or a
 * configuration file is specified as a property, those will be considered,
 * otherwise this auto-configuration will attempt to register mappers based on
 * the interface definitions in or under the root auto-configuration package.
 *
 * @author Eddú Meléndez
 * @author Josh Long
 * @author Kazuki Shimizu
 * @author Eduardo Macarrón
 */
@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
//@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MybatisAutoConfiguration.class);

    private JHipsterProperties.Mybatis properties;

    //    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    public MybatisAutoConfiguration(JHipsterProperties properties, ResourceLoader resourceLoader){
        this.properties = properties.getMybatis();
        this.resourceLoader = resourceLoader;
    }
//    public MybatisAutoConfiguration(JHipsterProperties properties,
//                                    ObjectProvider<Interceptor[]> interceptorsProvider,
//                                    ResourceLoader resourceLoader,
//                                    ObjectProvider<DatabaseIdProvider> databaseIdProvider) {
//        this.properties = properties.getMybatis();
//        this.interceptors = interceptorsProvider.getIfAvailable();
//        this.resourceLoader = resourceLoader;
//        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
//    }

    @PostConstruct
    public void checkConfigFileExists() {
        if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
            Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
            Assert.state(resource.exists(), "Cannot find config location: " + resource
                + " (please add config file or check your Mybatis configuration)");
        }
    }

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        factory.setConfiguration(properties.getConfiguration());
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        ExecutorType executorType = this.properties.getExecutorType();
        if (executorType != null) {
            return new SqlSessionTemplate(sqlSessionFactory, executorType);
        } else {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    @Bean
    public PageHelper pageHelper(DataSource dataSource) {
        logger.info("Registed the MyBatis PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("supportMethodsArguments", "false");
        p.setProperty("autoRuntimeDialect", "true");
        p.setProperty("dialect", "mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
