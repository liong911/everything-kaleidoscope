package top.senseiliu.everything.kaleido.provider.controller;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.senseiliu.everything.kaleido.common.base.response.Result;
import top.senseiliu.everything.kaleido.facade.dto.GeneratorDTO;
import top.senseiliu.everything.kaleido.manager.generator.DbMetaInfoManager;
import top.senseiliu.everything.kaleido.manager.generator.JavaCodeGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 获取数据库元数据信息
 * 
 * @author liuguanliang
 * @since 2024-06-02 16:44:03
 */
@RestController
@RequestMapping("/generator")
public class GeneratorController {

    private static final Logger log = LoggerFactory.getLogger(GeneratorController.class);
    @Resource
    private DbMetaInfoManager dbMetaInfoManager;
    
    @Resource
    private JavaCodeGenerator codeGenerator;

    /**
     * 查询库名(模式名)
     */
    @GetMapping("/getSchema")
    public Result<List<String>> getSchema() {
        return Result.ok(dbMetaInfoManager.getSchema());
    }

    /**
     * 查询模式下的表列表
     */
    @GetMapping("/getTableNameList")
    public Result<List<String>> getTableNameList(@RequestParam(value = "schemaName") String schemaName) {
        return Result.ok(dbMetaInfoManager.getTableNameList(schemaName));
    }

    /**
     * 查询建表sql
     */
    @GetMapping("/getCreateTableSql")
    public Result<String> getCreateTableSql(@RequestParam(value = "schemaName") String schemaName,
                                            @RequestParam(value = "tableName") String tableName) {
        return Result.ok(dbMetaInfoManager.getCreateTableSql(schemaName, tableName));
    }

    /**
     * 查询模板类型列表
     */
    @GetMapping("/getTemplateTypeList")
    public Result<List<String>> getTemplateTypeList() {
        return Result.ok(dbMetaInfoManager.getTemplateTypeList());
    }
    
    /**
     * 生成Java代码
     */
    @PostMapping("/generateCode")
    public Result<Map<String, String>> generateCode(@RequestBody @Validated GeneratorDTO dto) {
        log.info("generate code dto={}", dto);
        return Result.ok(codeGenerator.generate(dto));
    }

    /**
     * 下载Java代码
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response, @RequestBody @Validated GeneratorDTO dto) {
        log.info("download code dto={}", dto);
        Map<String, String> generateMap = codeGenerator.generate(dto);
        if (MapUtils.isEmpty(generateMap)) {
            return;
        }

        // 设置响应头
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", 
                String.format("attachment; filename=%s.%s.zip", dto.getSchemaName(), dto.getTableName()));

        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            for (Map.Entry<String, String> entry : generateMap.entrySet()) {
                // 创建新的ZIP文件条目
                ZipEntry zipEntry = new ZipEntry(entry.getKey());
                zos.putNextEntry(zipEntry);

                // 写入文件内容
                zos.write(entry.getValue().getBytes());
                zos.closeEntry();
            }
        } catch (IOException e) {
            ServletUtil.write(response, JSONObject.toJSONString(Result.fail()), ContentType.JSON.getValue());
            throw new RuntimeException(e);
        }
    }

}
