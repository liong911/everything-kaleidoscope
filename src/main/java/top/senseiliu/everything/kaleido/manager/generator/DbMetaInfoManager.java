package top.senseiliu.everything.kaleido.manager.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.senseiliu.everything.kaleido.dal.mapper.generator.DbMetaInfoMapper;
import top.senseiliu.everything.kaleido.facade.constant.TemplateTypeEnum;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DbMetaInfoManager {

    @Resource
    private DbMetaInfoMapper dbMetaInfoMapper;

    /**
     * 查询库名(模式名)
     */
    public List<String> getSchema() {
        log.info("getSchema");
        return dbMetaInfoMapper.selectSchemataNameList();
    }

    /**
     * 查询模式下的表列表
     */
    public List<String> getTableNameList(String schemaName) {
        return dbMetaInfoMapper.selectTableNameList(schemaName);
    }

    /**
     * 查询模板类型列表
     */
    public List<String> getTemplateTypeList() {
        return Arrays.stream(TemplateTypeEnum.values())
                .map(Enum::name).collect(Collectors.toList());
    }
    
    /**
     * 查询建表sql
     */
    public String getCreateTableSql(String schemaName, String tableName) {
        Map<String, String> map = dbMetaInfoMapper.selectCreateTableSql(schemaName, tableName);
        return map.get("Create Table");
    }
    
}
