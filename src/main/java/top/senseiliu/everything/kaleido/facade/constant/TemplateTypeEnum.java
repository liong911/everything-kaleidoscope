package top.senseiliu.everything.kaleido.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 内部定义的模板类型
 */
@Getter
@AllArgsConstructor
public enum TemplateTypeEnum {
    
    DTO("dto"),
    ENTITY("model"),
    EXAMPLE("example"),
    MAPPER("mapper"),
    XML("xml"),
    MANAGER("manager"),
    SERVICE("service"), 
    SERVICE_IMPL("service.impl"),
    ;
    
    private final String value;

    static Map<String, TemplateTypeEnum> itemMap = new HashMap<>();

    static {
        for (TemplateTypeEnum item : TemplateTypeEnum.values()) {
            itemMap.put(item.getValue(), item);
        }
    }

    public static TemplateTypeEnum convertFrom(String value) {

        return itemMap.get(value);
    }
}
