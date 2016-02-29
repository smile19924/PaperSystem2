package com.project.common.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;

import com.project.util.Toolkits;

/**
 * 切面程序
 * */
@Aspect
@Slf4j
public class ServiceLogInterceptor {

	public static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 接口日志字符串表达的最大长度
	 */
	public static final int INTERFACE_LOG_LEN = 3000;

	@Pointcut("execution(public * com.project.service.*ServiceImpl.*(..))")
	public void ServiceInterfaceLogPointcut() {

	}

	@Around(value = "ServiceInterfaceLogPointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		long beginTime = System.currentTimeMillis();
		String methodName = pjp.getSignature().getName();
		String paramInfo = buildArgsString(pjp);
		Object returnObj = null;// 返回信息
		log.info("Service【" + methodName + "】 input params is " + paramInfo);
		try {
			returnObj = pjp.proceed();
			long endTime = System.currentTimeMillis();
			long cost = endTime - beginTime;
			log.info("Service【" + methodName + "】 output data is " + returnObj + ", cost time " + cost + " ms.");
		} catch (Throwable e) {
			log.error("Service【" + methodName + "】break down when invoking：" + Toolkits.getExceptionInfo(e));
			throw new Exception(e);
		}
		return returnObj;
	}

	/**
	 * 拼接入参字符串
	 * 
	 * @param pjp
	 * @return String类型的入参字符串拼接
	 * */
	private static String buildArgsString(ProceedingJoinPoint pjp) {
		Object[] args = pjp.getArgs();
		StringBuffer paramInfo = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			if (i < args.length - 1) {
				paramInfo.append(args[i] + "|");
			} else {
				paramInfo.append(args[i]);
			}
		}
		return paramInfo.toString();
	}

}
