/**
 * 
 */
package com.userrestmanager.app.userrestmanager.common.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.userrestmanager.app.userrestmanager.common.log.LogConstant;

/**
 * @author PastoreGu
 *
 */
@Component
public abstract class AbstractService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final String serviceName = this.getClass().getSimpleName();

	protected void debugLog(final String methodName, final String customText, final Object... params) {

		this.logger.debug("'" + this.serviceName + "', Method='" + methodName + "' - " + customText, params);
	}

	protected void endLog(final String methodName, final Object... results) {
		this.logger.info(LogConstant.LOG_END_RESULT_HEADER, LogConstant.LOG_SERVICE_LABEL, this.serviceName, methodName,
				results.length == 0 ? LogConstant.RESPONSE_NULL : results);
	}

	protected void errorLog(final String methodName, final Exception e) {
		this.logger.error(LogConstant.LOG_END_ERROR_HEADER, LogConstant.LOG_SERVICE_LABEL, this.serviceName, methodName,
				e.getMessage());
	}

	protected void errorLog(final String methodName, final String errorMsg) {
		this.logger.error(LogConstant.LOG_END_ERROR_HEADER, LogConstant.LOG_SERVICE_LABEL, this.serviceName, methodName,
				errorMsg);
	}

	protected void infoLog(final String methodName, final String customText, final Object... params) {
		this.logger.info(LogConstant.LOG_BASIC_HEADER, this.serviceName, methodName, customText,
				params.length == 0 ? LogConstant.NO_PARAMETER : params);
	}

	protected String retrieveIdResourceFromHeaderResponseURI(final ResponseEntity<String> responseEntity) {
		final String methodName = "retrieveIdResourceFromHeaderResponseURI(responseEntity)";
		this.startLog(methodName);
		this.debugLog(methodName, "retrieveIdResourceFromHeaderResponseURI - retrieving URI");

		if ((responseEntity == null) || (responseEntity.getHeaders().getLocation() == null)) {
			this.logger.error(
					"retrieveIdResourceFromHeaderResponseURI - responseEntity without location header -> throwing IllegalArgumentException");
			throw new IllegalArgumentException();
		}

		final URI idResource = responseEntity.getHeaders().getLocation();
		String idResourceString = idResource.getPath();
		if ((idResourceString != null) && idResourceString.contains("/")) {
			final String[] idUserIdmStringParts = idResourceString.split("/");
			idResourceString = idUserIdmStringParts[idUserIdmStringParts.length - 1];

		}

		this.endLog(methodName, idResourceString);
		return idResourceString;
	}

	protected void startLog(final String methodName, final Object... params) {
		this.logger.info(LogConstant.LOG_START_HEADER, LogConstant.LOG_SERVICE_LABEL, this.serviceName, methodName,
				params.length == 0 ? LogConstant.NO_PARAMETER : params);
	}

	protected void stopWatchLog(final String methodName, final StopWatch stopWatch) {
		if (stopWatch != null) {
			if (stopWatch.isRunning()) {
				stopWatch.stop();
			}
			final StopWatch.TaskInfo[] taskInfos = stopWatch.getTaskInfo();

			for (final StopWatch.TaskInfo taskInfo : taskInfos) {
				if (taskInfo != null) {
					this.logger.info(LogConstant.LOG_BASIC_HEADER, LogConstant.LOG_SERVICE_LABEL, this.serviceName,
							methodName, String.format("Task: '%s' completed in '%d' millisecs", taskInfo.getTaskName(),
									taskInfo.getTimeMillis()));
				}
			}
		}
	}

	protected void warnLog(final String methodName, final String customText, final Object... params) {

		this.logger.warn("'" + this.serviceName + "', Method='" + methodName + "' - " + customText, params);
	}
}
