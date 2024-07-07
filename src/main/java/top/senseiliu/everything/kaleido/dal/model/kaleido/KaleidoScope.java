package top.senseiliu.everything.kaleido.dal.model.kaleido;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 万花筒导航表(KaleidoScope)表实体类
 *
 * @author liong.liu
 * @since 2024-06-29 17:04:30
 */
@Data
@Accessors(chain = true)
@TableName("kaleido_scope")
public class KaleidoScope implements Serializable {
    private static final long serialVersionUID = -21416272988090447L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 模块图标
     */
    @TableField(value = "title_icon")
    private String titleIcon;

    /**
     * 对象名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 对象图标
     */
    @TableField(value = "name_icon")
    private String nameIcon;

    /**
     * 环境名称
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 优先级
     */
    @TableField(value = "priority")
    private Integer priority;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    private LocalDateTime created;

    /**
     * 更新时间
     */
    @TableField(value = "updated")
    private LocalDateTime updated;

}
