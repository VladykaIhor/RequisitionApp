package com.ivladyka.requisitionappapiusers.util;

import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.util.exceptions.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SMSCodeFilter
//        extends OncePerRequestFilter
{

//    private final AuthenticationFailureHandler authenticationFailureHandler;
//    private final SMSCodeFilter smsCodeFilter;
//    private final SmsCodeService smsCodeService
//
//    @Autowired
//    public SMSCodeFilter(AuthenticationFailureHandler authenticationFailureHandler,
//                         SMSCodeFilter smsCodeFilter,
//                         SmsCodeService smsCodeService) {
//        this.authenticationFailureHandler = authenticationFailureHandler;
//        this.smsCodeFilter = smsCodeFilter;
//        this.smsCodeService = smsCodeService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (StringUtils.equalsIgnoreCase("/login/mobile", request.getRequestURI())
//                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
//            try {
//                validateSmsCode(new ServletWebRequest(request));
//            } catch (ValidateCodeException e) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//                return;
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private void validateSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
//        String smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "otp");
//        String mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "phoneNumber");
//        if (StringUtils.isBlank(smsCodeInRequest)) {
//            throw new ValidateCodeException("Verification code cannot be empty!");
//        }
//        if (codeInSession == null) {
//            throw new ValidateCodeException("Verification code does not exist, please resend!");
//        }
//        if (codeInSession.isExpire()) {
//            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobile);
//            throw new ValidateCodeException("The verification code has expired, please resend!");
//        }
//        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), smsCodeInRequest)) {
//            throw new ValidateCodeException("The verification code is incorrect!");
//        }
//        sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE + mobile);
//    }
}
