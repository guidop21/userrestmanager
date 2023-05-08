/**
 * 
 */
package com.userrestmanager.app.userrestmanager.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.userrestmanager.app.userrestmanager.common.log.LogConstant;

/**
 * @author PastoreGu
 *
 */
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String controllerName = this.getClass().getSimpleName();

	/**
	 * @param request
	 * @return
	 */
	public String retrieveLoggedUserToken(final HttpServletRequest request) {
		return request.getHeader("authorization");

	}

	protected void endLog(final String methodName, final Object... results) {
		this.logger.info(LogConstant.LOG_END_RESULT_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName,
				methodName, results.length == 0 ? LogConstant.RESPONSE_NULL : results);
	}

	protected void errorAndStackLog(final String methodName, final Exception e) {
		this.logger.error(
				String.format("%s : %s : %s", LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName, methodName),
				e);
		this.logger.error(LogConstant.LOG_END_ERROR_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName,
				methodName, ExceptionUtils.getMessage(e));

	}

	protected void errorLog(final String methodName, final Exception e) {
		this.logger.error(LogConstant.LOG_END_ERROR_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName,
				methodName, e.getMessage());
		this.logger.error(LogConstant.LOG_STACK_TRACE_HEADER, e);
	}

	protected void errorLog(final String methodName, final String errorMessage) {
		this.logger.error(LogConstant.LOG_END_ERROR_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName,
				methodName, errorMessage);
	}

	protected void startLog(final String methodName, final Object... params) {
		this.logger.info(LogConstant.LOG_START_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL, this.controllerName,
				methodName, params.length == 0 ? LogConstant.NO_PARAMETER : params);
	}

	protected void stopWatchLog(final String methodName, final StopWatch stopWatch) {
		if (stopWatch != null) {
			if (stopWatch.isRunning()) {
				stopWatch.stop();
			}
			final StopWatch.TaskInfo[] taskInfos = stopWatch.getTaskInfo();

			for (final StopWatch.TaskInfo taskInfo : taskInfos) {
				if (taskInfo != null) {
					this.logger.info(LogConstant.LOG_BASIC_HEADER, LogConstant.LOG_REST_CONTROLLER_LABEL,
							this.controllerName, methodName, String.format("Task: '%s' completed in '%d' millisecs",
									taskInfo.getTaskName(), taskInfo.getTimeMillis()));
				}
			}
		}
	}

}
