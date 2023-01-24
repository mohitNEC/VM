package eu.neclab.ngsildbroker.commons.datatypes.results;

import eu.neclab.ngsildbroker.commons.enums.ErrorType;

/**
 * @author hebgen
 * @version 1.0
 * @created 11-Jun-2018 11:13:22
 */
abstract class BaseResult {

	private String errorMsg;
	private ErrorType errorType;
	private int shortErrorMsg;
	private boolean success;

	BaseResult(String errorMsg, ErrorType errorType, int shortErrorMsg, boolean success) {
		super();
		this.errorMsg = errorMsg;
		this.errorType = errorType;
		this.shortErrorMsg = shortErrorMsg;
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public int getShortErrorMsg() {
		return shortErrorMsg;
	}

	public void setShortErrorMsg(int shortErrorMsg) {
		this.shortErrorMsg = shortErrorMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void finalize() throws Throwable {

	}

}