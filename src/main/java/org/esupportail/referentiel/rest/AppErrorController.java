package org.esupportail.referentiel.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AppErrorController extends AbstractErrorController { // NO_UCD (unused code)

	public AppErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/error")
	@ResponseBody
	public void handleError(HttpServletRequest request, HttpServletResponse response) {

		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> errorAttributes = getErrorAttributes(request,
				ErrorAttributeOptions.of(Include.MESSAGE, Include.EXCEPTION, Include.STACK_TRACE));

		/*
		 * {timestamp=Tue Mar 16 10:17:51 CET 2021, status=500, error=Internal Server
		 * Error, exception=java.lang.NullPointerException,
		 * trace=java.lang.NullPointerException: Cannot invoke
		 * "java.util.List.iterator()" because "tabInsAdmEtp" is null
		 */
		String timestamp = errorAttributes.get("timestamp").toString();
		
		String message = errorAttributes.get("message").toString();
		String error = errorAttributes.get("error").toString();
		String exception = "";
		if (errorAttributes.get("exception") != null)
			exception = errorAttributes.get("exception").toString();
		String trace = "";
		if (errorAttributes.get("trace") != null)
			trace = errorAttributes.get("trace").toString();
		String status = errorAttributes.get("status").toString();

		ErrorCustom err = new ErrorCustom(timestamp, message, error, exception, trace, status);

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter w = response.getWriter();
			w.write(objectMapper.writeValueAsString(err));
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		return new ResponseEntity<>(
//				errorAttributes, 
//		          HttpStatus.BAD_REQUEST);
//		    

//		final StringBuilder errorDetails = new StringBuilder();
//		errorAttributes.forEach((attribute, value) -> {
//			errorDetails.append("<tr><td>").append(attribute).append("</td><td><pre>").append(value)
//					.append("</pre></td></tr>");
//		});
//
//		return String.format("<html><head><style>td{vertical-align:top;border:solid 1px #666;}</style>"
//				+ "</head><body><h2>Error Page</h2><table>%s</table></body></html>", errorDetails.toString());
	}

	// @Override
	public String getErrorPath() {
		return "/error";
	}

	private class ErrorCustom {
		private String time;
		private String status;
		private String error;
		private String message;
		private String exception;
		@JsonIgnore
		private String trace;

		public ErrorCustom(String time, String cause) {
			super();
			this.time = time;
			this.setMessage(cause);
		}

		public ErrorCustom(String time, String message, String error, String exception, String trace, String status) {
			super();
			this.time = time;

			this.setMessage(message);
			this.error = error;
			this.exception = exception;
			this.trace = trace;
			this.status = status;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

		public String getTrace() {
			return trace;
		}

		public void setTrace(String trace) {
			this.trace = trace;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
