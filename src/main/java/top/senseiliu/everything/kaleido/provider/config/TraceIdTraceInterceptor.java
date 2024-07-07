package top.senseiliu.everything.kaleido.provider.config;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求链路id
 */
public class TraceIdTraceInterceptor implements HandlerInterceptor {
    public static final String TRACE_ID = "TRACE-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        MDC.put(TRACE_ID, getRequestId(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // 把requestId添加到响应头
        response.addHeader(TRACE_ID, MDC.get(TRACE_ID));
        // 请求完成，从MDC中的traceId
        MDC.remove(TRACE_ID);
    }

    public static String getRequestId(HttpServletRequest request) {
        String headerRequestId = request.getHeader(TRACE_ID);
        return StringUtils.hasLength(headerRequestId) ? headerRequestId : IdUtil.fastSimpleUUID();
    }
}
