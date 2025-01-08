package ar.gob.sanluislaciudad.munsl_sigem_backend.aspect

import groovy.util.logging.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
@Slf4j
class LogAspect {

	public static final String SANITIZED_VALUE = '········'
	private static final List<String> sensitiveFields = ["password"]

	@Pointcut("execution(public * ar.gob.sanluislaciudad.munsl_sigem_backend.controller..*.*(..))")
	void controllerPublicMethodsPointcut() {
		// Pointcut for public methods in controller package
	}

	@Before("controllerPublicMethodsPointcut()")
	static void logBefore(JoinPoint joinPoint) {
		def sanitizedArgs = sanitizeForLogging(joinPoint.args)
		log.info(
				"[${joinPoint.signature.toShortString()}] Args: ${sanitizedArgs}"
				)
	}

	@AfterReturning(pointcut = "controllerPublicMethodsPointcut()", returning = "result")
	static void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info(
				"[${joinPoint.signature.toShortString()}] Response: ${result}"
				)
	}

	private static Object sanitizeForLogging(Object[] args) {
		args.collect { arg ->
			if (arg instanceof Map) {
				arg.collectEntries { k, v ->
					isSensitiveField(k as String) ? [k, SANITIZED_VALUE] : [k, v]
				}
			} else if (arg instanceof String || arg instanceof CharSequence) {
				arg
			} else {
				try {
					def sanitizedCopy = arg.class.getDeclaredConstructor().newInstance()
					def fields = arg.class.declaredFields
					fields.each { field ->
						field.accessible = true
						def value = field.get(arg)
						if (isSensitiveField(field.name)) {
							field.set(sanitizedCopy, SANITIZED_VALUE)
						} else {
							field.set(sanitizedCopy, value)
						}
					}
					sanitizedCopy
				} catch (Exception ignored) {
					arg
				}
			}
		}
	}

	private static boolean isSensitiveField(String fieldName) {
		sensitiveFields.any { it.equalsIgnoreCase(fieldName) }
	}
}
