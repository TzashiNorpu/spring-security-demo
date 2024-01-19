package com.tzashinorpu.springsecuritydemo.config.web;

import com.tzashinorpu.springsecuritydemo.enums.ResponseEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomBaseException;
import com.tzashinorpu.springsecuritydemo.pojo.vo.ResponseResult;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Configuration
@Slf4j
public class CustomWebConfig {

	// 顶层异常处理
	@Controller
	public static class CustomErrorController implements ErrorController {
		@RequestMapping("/error")
		@ResponseBody
		public ResponseResult handlePageError(HttpServletRequest request) {
//			Throwable e = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			String msg = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
			String code = (String) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
			return ResponseResult.error(code,msg);
		}
	}

	@ControllerAdvice
	public static class CustomControllerAdvice implements ResponseBodyAdvice<Object> {
		@Override
		public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
			// 方法上是否由 ResponseBody 注解
			return returnType.getMethodAnnotation(ResponseBody.class) != null
					|| AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null;
		}

		@Override
		public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
			// swagger 接口返回的是字节数组
			if (body instanceof ResponseResult || body instanceof byte[]) return body;
			return ResponseResult.ok(ResponseEnums.SUCCESS.getMsg(), body);
		}
	}

	@RestControllerAdvice
	public static class CustomExceptionControllerAdvice {
		@ExceptionHandler(CustomBaseException.class)
		public ResponseResult customBaseExceptionHandler(CustomBaseException e) {
			return ResponseResult.error(e.getCode(), e.getMessage(), e.getData());
		}

		@ExceptionHandler(Exception.class)
		public ResponseResult exceptionHandler(Exception e) {
			return ResponseResult.error(ResponseEnums.ERROR.getCode(), e.getMessage(), null);
		}
	}
}
