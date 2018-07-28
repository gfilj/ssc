package com.project.common.util;


import com.project.common.exception.ArgumentException;
import com.project.common.exception.ExceptionEnum;

public class ArgumentsUtil {
	
	/**
	 * 校验对象参数，如果参数是空则抛出ArgumentException参数异常
	 * @param <T>
	 * @param arg
	 * @param code
	 * @param errorMsg
	 * @throws ArgumentException
	 */
	public static <T> void checkObjectBlank (T t, ExceptionEnum exceptionEnum, String errorMsg) throws ArgumentException {
		if (t == null) {
			throw new ArgumentException(exceptionEnum,errorMsg);
		}
	}
	
	/**
	 * 校验参数，接收boolean类型的表达式 (传过来的如果是true，说明是非法参数)
	 * @param expression
	 * @param code
	 * @param errorMsg
	 * @throws ArgumentException
	 */
	public static void checkArgument (boolean expression,ExceptionEnum exceptionEnum, String errorMsg) throws ArgumentException {
		if (expression) {
			throw new ArgumentException(exceptionEnum,errorMsg);
		}
	}

}
