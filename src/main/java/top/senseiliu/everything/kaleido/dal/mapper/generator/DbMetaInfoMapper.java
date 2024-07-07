package top.senseiliu.everything.kaleido.dal.mapper.generator;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据库元数据查询
 *
 * @author liuguanliang
 * @since 2024-06-02 13:13:45
 */
@DS("information_schema")
@Repository
public interface DbMetaInfoMapper {

    /**
     * 查询库名(模式名)
     */
    List<String> selectSchemataNameList();

    /**
     * 查询模式下的表列表
     */
    List<String> selectTableNameList(@Param("schemaName") String schemaName);

    /**
     * 查询建表脚本
     */
    Map<String, String> selectCreateTableSql(@Param("schemaName") String schemaName, @Param("tableName") String tableName);
}