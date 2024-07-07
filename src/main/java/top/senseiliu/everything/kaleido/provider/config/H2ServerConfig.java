package top.senseiliu.everything.kaleido.provider.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * H2数据库
 * 
 * @author liuguanliang
 */
@Configuration
public class H2ServerConfig {

    @Value("${h2-server.port}")
    private String h2Port;
    
    @Resource
    private DataSource dataSource;
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2Port);
    }

    @PostConstruct
    public void init() throws SQLException {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("init.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
    
}
