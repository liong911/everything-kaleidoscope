package top.senseiliu.everything.kaleido.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.senseiliu.everything.kaleido.common.base.exception.ErrorCode;

@Getter
@AllArgsConstructor
public enum KaleidoErrorCodeEnum implements ErrorCode {

    USER_NOT_EXIST(10001),
    USER_ID_EMPTY(10002);

    private int code;

}
