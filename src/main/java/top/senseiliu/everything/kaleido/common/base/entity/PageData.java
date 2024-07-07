package top.senseiliu.everything.kaleido.common.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1132434255476111679L;
    
    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 数据
     */
    private List<T> list;

}
