package com.distribution.wamoli.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.distribution.wamoli.config.liquibase.AsyncSpringLiquibase;
import liquibase.integration.spring.SpringLiquibase;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.core.env.Environment;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Configuration
//@EnableJpaRepositories("com.distribution.wamoli.repository")
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
//@ImportResource("classpath:spring/applicationContext-trans.xml")
public class DatabaseConfiguration{

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource ataSource(){
//        return DataSourceBuilder.create().build();
//    }


    private Environment env;
	
	public DatabaseConfiguration(Environment env){
		this.env = env;
	}

//    /**
//     * Open the TCP port for the H2 database, so it is available remotely.
//     *
//     * @return the H2 database TCP server
//     * @throws SQLException if the server failed to start
//     */
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
//    public Server h2TCPServer() throws SQLException {
//        return Server.createTcpServer("-tcp","-tcpAllowOthers");
//    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {

        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
        SpringLiquibase liquibase = new AsyncSpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE)) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }

}
