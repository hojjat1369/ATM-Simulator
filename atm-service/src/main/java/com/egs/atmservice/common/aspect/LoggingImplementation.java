package com.egs.atmservice.common.aspect;


import lombok.extern.slf4j.Slf4j;


@Slf4j
// @Component
// @Aspect
// @Order(1)
public class LoggingImplementation {

//	@Pointcut("@annotation(com.egs.atmservice.common.aspect.Logging))")
//	protected void logging() {
//
//		// For defining the Point Cut with annotation
//	}
//
//	@Around("logging()")
//	public Object authenticationService(ProceedingJoinPoint joinPoint) throws Throwable {
//
//		Object[] args = joinPoint.getArgs();
//
//		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
//		Method method = methodSignature.getMethod();
//
//		String methodName = method.getName();
//		long startTimeInNanos = System.nanoTime();
//		String date = formatDateForPrint(new Date());
//
//		log.info(String.format("start %s, input: %s, date: %s ", methodName, getInput(args), date));
//
//		Object output = null;
//		try{
//			output = joinPoint.proceed();
//		}
//		catch (Exception e){
//			log.error(String.format("end %s, input: %s, error: %s, date: %s", methodName, getInput(args), e.getMessage(), date));
//			throw e;
//		}
//		log.info(String.format("end %s, input: %s, output: %s, responseTime: %s milliseconds", methodName, getInput(args), output, (System.nanoTime() - startTimeInNanos) / 1000000));
//		return output;
//	}
//
//	private String getInput(Object[] args) {
//
//		if (args != null && args.length > 0)
//			return String.valueOf(args[0]);
//		return "";
//	}
//
//	private static String formatDateForPrint(Date startTime) {
//
//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
//		return formatter.format(startTime.getTime());
//	}

}
