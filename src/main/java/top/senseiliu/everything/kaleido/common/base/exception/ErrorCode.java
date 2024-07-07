package top.senseiliu.everything.kaleido.common.base.exception;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 *
 * 如：10001（10代表系统模块，001代表业务代码）
 *
 */
public interface ErrorCode {

    int INTERNAL_SERVER_ERROR = 500;
    int UNAUTHORIZED = 401;
    int RATE_LIMIT = 429;

    int METHOD_NOT_SUPPORTED = 405;
    
    int PARAMETER_INVALID = 400;

    /**
     * 获取编码
     */
    int getCode();

}
