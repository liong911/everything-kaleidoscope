package top.senseiliu.everything.kaleido.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import top.senseiliu.everything.kaleido.facade.constant.TemplateTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author dirtydamn
 */
@Data
@Accessors(chain = true)
public class GeneratorDTO implements Serializable {

    private static final long serialVersionUID = 1132434222476111679L;
    
    /**
     * 作者
     * @Demo yourName
     */
    private String author = "yourName";

    /**
     * 模式名(库名)
     */
    @NotBlank(message = "模式不能为空")
    private String schemaName;
    
    /**
     * 表名
     */
    @NotBlank(message = "名表不能为空")
    private String tableName;
    
    /**
     * 包名
     * @Demo com.xxx
     */
    private String packageName = "com.xxx";

    /**
     * 包下的模块名
     * @Demo ""
     */
    private String moduleName = "";

    /**
     * 模板类型
     */
    @NotEmpty(message = "模板不能为空")
    private List<TemplateTypeEnum> templateTypes;

    public String getPackageName() {
        if (StringUtils.isNotBlank(packageName)) {
            return packageName;
        }
        return "com.xxx";
    }

    public String getAuthor() {
        if (StringUtils.isNotBlank(author)) {
            return author;
        }
        return "yourName";
    }
}
