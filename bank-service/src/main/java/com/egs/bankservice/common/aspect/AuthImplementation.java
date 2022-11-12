package com.egs.bankservice.common.aspect;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.egs.bankservice.common.exception.DomainException;
import com.egs.bankservice.common.util.JwtTokenUtil;


@Component
@Aspect
@Order(1)
public class AuthImplementation {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Pointcut("@annotation(com.egs.bankservice.common.aspect.Auth))")
	protected void authentication() {

		// For defining the Point Cut with annotation
	}

	@Around("authentication()")
	public Object authenticationService(ProceedingJoinPoint joinPoint) throws Throwable {

		return validateSessionToken(joinPoint);
	}

	private Object validateSessionToken(ProceedingJoinPoint joinPoint) throws Throwable {

		doSessionTokenAuthentication(joinPoint);

		return joinPoint.proceed();
	}

	private void doSessionTokenAuthentication(ProceedingJoinPoint joinPoint) throws DomainException {

		String header = getSessionIdFromHeader(joinPoint);
		if (hasAuthorizationBearer(header))
			jwtTokenUtil.validateAccessToken(getAccessToken(header));
	}

	private String getSessionIdFromHeader(ProceedingJoinPoint joinPoint) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method = methodSignature.getMethod();
		return getSessionIdStringFromMethodAnnot(method, joinPoint.getArgs());
	}

	public static String getSessionIdStringFromMethodAnnot(Method method, Object[] parameters) {

		Annotation[][] methodAnnotations = method.getParameterAnnotations();

		if (methodAnnotations != null){
			int index = -1;

			for (int i = 0; i < methodAnnotations.length; i++){

				if (methodAnnotations[i].length > 0 && (methodAnnotations[i][0].annotationType().equals(RequestHeader.class))){
					index = i;
					break;
				}

			}
			if (index != -1){
				return (String) parameters[index];
			}
		}
		return null;
	}

	private boolean hasAuthorizationBearer(String header) {

		return (header != null && !header.isEmpty() && header.startsWith("Bearer"));
	}

	private String getAccessToken(String header) {

		return header.split(" ")[1].trim();
	}

}
