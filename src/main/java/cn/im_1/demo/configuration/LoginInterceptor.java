package cn.im_1.demo.configuration;

import cn.im_1.demo.entity.dto.BaseResponse;
import cn.im_1.demo.entity.po.PUser;
import cn.im_1.demo.utils.RedisUtil;
import cn.im_1.demo.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.im_1.demo.entity.dto.BaseResponse.CODE_EXPIRE;


@Configuration
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //业务代码
        if (!checkToken(request)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            ServletOutputStream out = response.getOutputStream();

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(CODE_EXPIRE);
            baseResponse.setMsg("登陆过期，请重新登录");

            byte[] bytes = JSON.toJSONString(baseResponse).getBytes();
            out.write(bytes);
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 检查token
     *
     * @param request 请求体
     * @return PUser
     */
    private boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean isAccept = false;
        if (StringUtil.isNotEmpty(token)) {
            PUser user = (PUser) RedisUtil.getInstance().get(token);
            if (isAccept = (user != null)) {
                RedisUtil.getInstance().expire(token, 7200);
            }
        }
        return isAccept;
    }
}
