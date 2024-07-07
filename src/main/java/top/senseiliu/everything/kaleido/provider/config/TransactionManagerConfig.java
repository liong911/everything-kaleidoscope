package top.senseiliu.everything.kaleido.provider.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置
 *
 * @author liuguanliang
 */
@Configuration
@MapperScan({"top.senseiliu.everything.kaleido.dal.mapper"})
public class TransactionManagerConfig {

    /**
     * 事务
     *
     * @return
     */
    @Bean("transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
