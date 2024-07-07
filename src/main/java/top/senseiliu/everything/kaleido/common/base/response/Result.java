package top.senseiliu.everything.kaleido.common.base.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -2589861911803573544L;
    
    /**
     * 错误码
     * 
     * @demo 0
     */
    private Integer code;
    
    /**
     * 错误信息
     * 
     * @demo sucess
     */
    private String msg;
    
    /**
     * 返回数据
     */
    private T data;

    public Result() {
    }
    
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        Result<T> result = new Result<T>();
        result.setCode(-1);
        result.setMsg("服务异常");
        result.setData(null);
        return result;
    }
}
