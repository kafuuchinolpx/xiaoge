package cn.xiaoge.design.interceptor;

import cn.xiaoge.design.entity.vo.ReturnBean;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class FirstInterceptor implements HandlerInterceptor {

    /**
     * 该方法在目标方法之前被调用.
     * 若返回值为 true, 则继续调用后续的拦截器和目标方法.
     * 若返回值为 false, 则不会再调用后续的拦截器和目标方法.
     * 可以考虑做权限. 日志, 事务等.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
//        HttpSession session = request.getSession();
//        Object loginUser = session.getAttribute("loginUser");
//        if (loginUser == null) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(ReturnBean.of(ReturnBean.AnswerCode.NOT_LOGIN)));
//            return false;
//        }
        return true;

    }

    /**
     * 调用目标方法之后, 但渲染视图之前.
     * 可以对请求域中的属性或视图做出修改.
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    /**
     * 渲染视图之后被调用. 释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
    }

}
