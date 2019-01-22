package com.zengtengpeng.common.exception;

import com.zengtengpeng.common.bean.DataRes;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {


    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataRes jsonErrorHandler(HttpServletRequest request, HttpServletResponse response,
                                    Exception e) throws IOException {
        logger.error("程序出错了", e);
        String ajax = request.getHeader("X-Requested-With");
        String desc = "出错了,请稍后再试,或者联系管理员.异常信息->" + e.getMessage();
        if ("XMLHttpRequest".equals(ajax)) {
            DataRes error = DataRes.error(ResponseCode.ERROR500.code(), desc);
            error.setData(new Page());
            return error;
        }else {
            response.getWriter().print("<script>window.location.href ='"+request.getContextPath()+"/error/goto500?message="+desc+"'</script>");
            return null;
        }
    }
}