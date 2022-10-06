package net.chenlin.dp.common.exception;

import net.chenlin.dp.common.entity.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @author wang<fangyuan.co@outlook.com>
 */
@RestControllerAdvice
public class RRExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	/**
	 * 自定义异常
	 */
	@ExceptionHandler({GoLoginException.class})
	public R handleGoLoginException(GoLoginException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	/**
	 * 新增异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	/**
	 * 通用异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
	
}
