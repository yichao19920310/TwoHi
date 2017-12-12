package com.yichao.log4j;

public interface Log4j {

	public abstract void logError(Throwable e,String info);
	public abstract void logInfo(String info);
}
