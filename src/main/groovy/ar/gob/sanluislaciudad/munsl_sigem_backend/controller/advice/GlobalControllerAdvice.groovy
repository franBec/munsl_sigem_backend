package ar.gob.sanluislaciudad.munsl_sigem_backend.controller.advice

import groovy.util.logging.Slf4j
import io.opentelemetry.api.trace.Span
import java.time.Instant
import java.time.format.DateTimeFormatter
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Slf4j
class GlobalControllerAdvice {

	private static ProblemDetail buildProblemDetail(Exception e, HttpStatus status) {
		String exceptionSimpleName = e.class.simpleName
		String logMessage = "${exceptionSimpleName} being handled"

		if (status.is5xxServerError()) {
			log.error(logMessage, e)
		} else if (status.is4xxClientError()) {
			log.warn(logMessage, e)
		} else {
			log.info(logMessage, e)
		}

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.localizedMessage)
		problemDetail.title = exceptionSimpleName
		problemDetail.setProperty("timestamp", DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
		problemDetail.setProperty("trace", Span.current().spanContext.traceId)
		problemDetail
	}

	@ExceptionHandler(BadCredentialsException)
	ProblemDetail handle(BadCredentialsException e) {
		buildProblemDetail(e, HttpStatus.UNAUTHORIZED)
	}

	@ExceptionHandler(Exception)
	ProblemDetail handle(Exception e) {
		buildProblemDetail(e, HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@ExceptionHandler(MethodArgumentNotValidException)
	ProblemDetail handle(MethodArgumentNotValidException e) {
		buildProblemDetail(e, HttpStatus.BAD_REQUEST)
	}
}
