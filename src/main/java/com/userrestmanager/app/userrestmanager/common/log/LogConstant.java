/**
 * 
 */
package com.userrestmanager.app.userrestmanager.common.log;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author PastoreGu
 *
 */
public final class LogConstant {

	public static final String LOG_START_HEADER = "START '{}'='{}', Method='{}', Params='{}'";
	public static final String LOG_BASIC_HEADER = "'{}', Method='{}' - {}, Params='{}'";

	public static final String LOG_END_RESULT_HEADER = "EXIT '{}'='{}', Method='{}', Result='{}'";
	public static final String LOG_END_ERROR_HEADER = "EXIT '{}'='{}', Method='{}', Error='{}'";
	public static final String LOG_STACK_TRACE_HEADER = "The stack trace is :";
	public static final String LOG_GENERIC_ERROR = "GENERIC_ERROR";
	public static final String NO_PARAMETER = "<NO_PARAM>";

	public static final String RESPONSE_NULL = "<RESPONSE_NULL>";
	public static final String LOG_REST_CONTROLLER_LABEL = "Controller";

	public static final String LOG_SERVICE_LABEL = "Service";
	public static final String LOG_SUCCESS = "SUCCESS";

	private LogConstant() {
		throw new NotImplementedException("Cannot instantiate this class");
	}
}