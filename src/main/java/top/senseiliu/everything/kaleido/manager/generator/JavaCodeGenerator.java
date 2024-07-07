package top.senseiliu.everything.kaleido.manager.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.senseiliu.everything.kaleido.facade.constant.TemplateTypeEnum;
import top.senseiliu.everything.kaleido.facade.dto.GeneratorDTO;
import top.senseiliu.everything.kaleido.manager.config.GeneratorProperties;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Java代码生成器
 * 
 * @author liuguanliang
 */
@Slf4j
@Component
public class JavaCodeGenerator {
    
    @Value("${code-generator.templatePath}")
    private String templatePath;
    
    @Resource
    private GeneratorProperties generatorProperties;
    
    public Map<String, String> generate(GeneratorDTO dto) {
        EnhanceVelocityTemplateEngine engine = new EnhanceVelocityTemplateEngine(templatePath);
        FastAutoGenerator.create(String.format(generatorProperties.getUrl(), dto.getSchemaName()), generatorProperties.getName(), generatorProperties.getPassword())
                .globalConfig(builder -> builder
                        .author(dto.getAuthor())
                        .outputDir("")
                        .disableOpenDir()
                        .commentDate("yyyy-MM-dd hh:mm:ss"))
                .packageConfig(builder -> builder
                        .parent(dto.getPackageName())
                        .moduleName(StringUtils.defaultIfBlank(dto.getModuleName(), ""))
                        .entity(TemplateTypeEnum.ENTITY.getValue())
                        .mapper(TemplateTypeEnum.MAPPER.getValue())
                        .xml(TemplateTypeEnum.XML.getValue())
                        .service(TemplateTypeEnum.SERVICE.getValue())
                        .serviceImpl(TemplateTypeEnum.SERVICE_IMPL.getValue()))
                .templateConfig(builder -> builder.disable())
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    dto.getTemplateTypes().forEach(o -> {
                        switch (o) {
                            case DTO:
                                customFile.put("DTO.java", "dto.java.vm");
                                break;
                            case ENTITY:
                                customFile.put("entity.java", "dto.java.vm");
                                break;
                            case EXAMPLE:
                                customFile.put("example.java", "example.java.vm");
                                break;
                            case MAPPER:
                                customFile.put("mapper.java", "mapper.java.vm");
                                break;
                            case XML:
                                customFile.put("mapper.xml", "mapper.xml.vm");
                                break;
                            case MANAGER:
                                customFile.put("manager.java", "manager.java.vm");
                                break;
                            case SERVICE:
                                customFile.put("service.java", "service.java.vm");
                                break;
                            case SERVICE_IMPL:
                                customFile.put("serviceImpl.java", "serviceImpl.java.vm");
                                break;
                        }
                    });
                    consumer.customFile(customFile);

                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("randomSerialVersionUID", new Object() {
                        @Override
                        public String toString() {
                            return String.valueOf(new Random().nextLong());
                        }
                    });
                    consumer.customMap(customMap);
                })
                .strategyConfig(builder -> builder
                        .addInclude(dto.getTableName()))
                .templateEngine(engine)
                .execute();
        return engine.getOutput();
    }
    
}
