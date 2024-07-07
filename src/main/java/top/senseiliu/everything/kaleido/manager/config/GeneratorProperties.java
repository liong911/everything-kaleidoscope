package top.senseiliu.everything.kaleido.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "code-generator")
public class GeneratorProperties {
    
    private String url;
    
    private String name;
    
    private String password;
    
}
