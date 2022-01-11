package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.transfer.ApiError;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomRestExceptionHandler extends AbstractLogComponent {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse<?>> handleAllExceptions(final Exception ex, final WebRequest request) {
		logger.error("Unexpected exception occurred.", ex);
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request)).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ApiResponse<?>> handleNonExistence(final NoSuchElementException ex,
																   final WebRequest request) {
		logger.error("Reference to a non-existent object.", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(
				getApiError(ex, HttpStatus.NOT_FOUND, request, "Reference to a non-existent object.")).build(),
									HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<ApiResponse<?>> handleIOException(final IOException ex, final WebRequest request,
																  final HttpServletResponse response) {
		logger.error("Failed or interrupted I/O operation.", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(
				getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request, response,
							"Failed or interrupted IO operation.")).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ApiResponse<?>> handleIllegalArgument(final IllegalArgumentException ex,
																	  final WebRequest request) {
		logger.error("A method has been passed an illegal or inappropriate argument.", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request,
																			   "A method has been passed an illegal or inappropriate argument."))
											   .build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public final ResponseEntity<ApiResponse<?>> handleRequestTimeout(final AsyncRequestTimeoutException ex,
																	 final WebRequest request) {
		logger.error("Request timeout: maximum response time reached.", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(
				getApiError(ex, HttpStatus.SERVICE_UNAVAILABLE, request,
							"Request timeout: maximum response time reached.")).build(),
									HttpStatus.SERVICE_UNAVAILABLE);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), ex.getMessage(), path);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request,
								 String customMessage) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), customMessage != null ? customMessage : ex.getMessage(), path);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request,
								 final HttpServletResponse response, String customMessage) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return new ApiError(status.value(), customMessage != null ? customMessage : ex.getMessage(), path);
	}
}
