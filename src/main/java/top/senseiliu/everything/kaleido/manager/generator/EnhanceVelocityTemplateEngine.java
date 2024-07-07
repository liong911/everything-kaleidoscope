package top.senseiliu.everything.kaleido.manager.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.jetbrains.annotations.NotNull;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 写入内存的模板引擎配置
 * 
 * @author liuguanliang
 */
public class EnhanceVelocityTemplateEngine extends VelocityTemplateEngine {

    private VelocityEngine velocityEngine;
    private final Map<String, String> outputMap = new HashMap<>();
    private final String templatePath;
    
    public EnhanceVelocityTemplateEngine(String templatePath) {
        this.templatePath = templatePath;
    }
    
    @Override
    public @NotNull VelocityTemplateEngine init(@NotNull ConfigBuilder configBuilder) {
        if (null == velocityEngine) {
            Properties p = new Properties();
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templatePath);
            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
            p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", StringPool.TRUE);
            velocityEngine = new VelocityEngine(p);
        }
        return this;
    }
    
    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        customFile.forEach((key, templatePath) -> {
            StringWriter sw = new StringWriter();
            Template template = velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
            template.merge(new VelocityContext(objectMap), sw);
            outputMap.put(tableInfo.getEntityName() + StrUtil.upperFirst(key), sw.toString());
        });
    }
    
    public Map<String, String> getOutput() {
        return outputMap;
    }
    
}
