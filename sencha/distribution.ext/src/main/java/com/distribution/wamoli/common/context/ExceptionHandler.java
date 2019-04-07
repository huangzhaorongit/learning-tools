package com.distribution.wamoli.common.context;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.distribution.wamoli.common.exception.AjaxException;
import com.distribution.wamoli.common.exception.ApplicationRuntimeException;
import com.distribution.wamoli.common.pojo.ErrorData;
import com.distribution.wamoli.common.pojo.ErrorMessage;

/**
 * 创 建 人：gugu
 * 创建日期：2015-04-20
 * 修 改 人：
 * 修改日 期：
 * 描   述：异常拦截器，用异常的统一拦截，区分ajax请求与普通请求。
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	@Autowired
	MessageService messageService;

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//控制台打印错误
		ex.printStackTrace();
		if (ex instanceof ApplicationRuntimeException ||ex instanceof IllegalArgumentException) {
			if ("XMLHttpRequest".equalsIgnoreCase(request
					.getHeader("X-Requested-With"))||ex instanceof AjaxException) {// 不是ajax请求
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				String result = null;
				String type = "";
				if (request.getParameterMap().containsKey("format")) {
					type = request.getParameter("format");
				}
				if (type.equals("xml")) {
					ErrorData error =new ErrorData();
					ErrorMessage message = new ErrorMessage();
					message.setCode(-1);
					message.setMessage(ex.getMessage());
					error.getAction().add(message);
					try {
						JAXBContext context = JAXBContext
								.newInstance(ErrorData.class);
						Marshaller marshaller = context.createMarshaller();
						marshaller.setProperty(
								Marshaller.JAXB_FORMATTED_OUTPUT, true);
						marshaller.setProperty(Marshaller.JAXB_ENCODING,
								"UTF-8");

						StringWriter writer = new StringWriter();
						marshaller.marshal(error, writer);
						result = writer.toString();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					ErrorMessage message = new ErrorMessage();
					message.setCode(-1);
					message.setMessage(ex.getMessage());
					ObjectMapper objectMapper = new ObjectMapper();

					try {
						result = objectMapper.writeValueAsString(message);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				try {
					PrintWriter writer = response.getWriter();
					writer.write(result);
					writer.flush();
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				MessageInfo info = new MessageInfo();
				info.setMessage(ex.getMessage());

				messageService.set(request.getSession().getId(),info);
				ModelAndView mav=new ModelAndView();
				mav.setViewName("redirect:/msg");
				return mav;
			}

		}
		return null;

	}

}
