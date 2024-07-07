package top.senseiliu.everything.kaleido.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author dirtydamn
 */
@Data
@Accessors(chain = true)
public class KaleidoDTO implements Serializable {

    private static final long serialVersionUID = 1111434222476111679L;
    
    /**
     * 模块标题
     */
    private String title;

    /**
     * 模块图标
     */
    private String titleIcon;

    /**
     * 模块图标
     */
    private List<ServiceDTO> serviceItems;

    @Data
    @Accessors(chain = true)
    public static class ServiceDTO {
        /**
         * 对象名称
         */
        private String name;

        /**
         * 对象图标
         */
        private String nameIcon;
        
        /**
         * 对象图标
         */
        private List<EnvironmentDTO> environments;
    }

    @Data
    @Accessors(chain = true)
    public static class EnvironmentDTO {
        /**
         * 环境名称
         */
        private String tag;

        /**
         * 链接
         */
        private String url;

        /**
         * 账号
         */
        private String account;

        /**
         * 密码
         */
        private String password;
    }
    
}
