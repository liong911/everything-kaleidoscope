package top.senseiliu.everything.kaleido.generator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.senseiliu.everything.kaleido.EverythingKaleidoApplication;
import top.senseiliu.everything.kaleido.facade.constant.TemplateTypeEnum;
import top.senseiliu.everything.kaleido.facade.dto.GeneratorDTO;
import top.senseiliu.everything.kaleido.manager.generator.JavaCodeGenerator;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@SpringBootTest(classes = EverythingKaleidoApplication.class)
class JavaCodeGeneratorTests {
    
    @Resource
    private JavaCodeGenerator codeGenerator;
    
    @Test
    public void test() {
        GeneratorDTO dto = new GeneratorDTO();
        dto.setAuthor("liuguanliang")
                .setSchemaName("my-test")
                .setTableName("user_info")
                .setPackageName("com")
                .setModuleName("user")
                .setTemplateTypes(Arrays.asList(TemplateTypeEnum.ENTITY));
        Map<String, String> generator = codeGenerator.generate(dto);
        log.info("结果={}", generator);
    }
}
