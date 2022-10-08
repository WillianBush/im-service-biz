package net.chenlin.dp.common.utils;

import net.chenlin.dp.common.constant.MsgConstant;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 通用工具类
 * @author wang<fangyuan.co@outlook.com>
 */
public class CommonUtils {


	/**
	 * 根据文件绝对路径获取目录
	 * @param filePath
	 * @return
	 */
	public static String getPath(String filePath) {
		String path = "";
		if (StringUtils.isNotBlank(filePath)) {
			path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		}
		return path;
	}

	/**
	 * 根据文件绝对路径获取文件
	 * @param filePath
	 * @return
	 */
	public static String getFile(String filePath) {
		String file = "";
		if (StringUtils.isNotBlank(filePath)) {
			file = filePath.substring(filePath.lastIndexOf("/") + 1);
		}
		return file;
	}

	/**
	 * 对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
        return obj == null;
    }

	/**
	 * 判断整数是否大于零
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isIntThanZero(int number) {
        return number > 0;
    }
	
	/**
	 * 新增，修改提示
	 * @param count
	 * @return
	 */
	public static R msg(int count) {
		if(isIntThanZero(count)){
			return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}
		return R.error(MsgConstant.MSG_OPERATION_FAILED);
	}
	
	/**
	 * 查询详情提示
	 * @param data
	 * @return
	 */
	public static R msg(Object data) {
		if(isNullOrEmpty(data)){
			return R.error(MsgConstant.MSG_INIT_FORM);
		}
		return R.ok().put(SystemConstant.DATA_ROWS, data);
	}

	public static  <T> Resp<T>  msgResp(int count) {
		if(isIntThanZero(count)){
			return Resp.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}
		return Resp.error(MsgConstant.MSG_OPERATION_FAILED);
	}

	public static <T> Resp<T> msgResp(T data) {
		if(isNullOrEmpty(data)){
			return Resp.error(MsgConstant.MSG_INIT_FORM);
		}
		return Resp.ok().ok(data);
	}

	public static <T> Resp<T> msgResp(List<T> dataRows) {
		if(isNullOrEmpty(dataRows)){
			return Resp.error(MsgConstant.MSG_INIT_FORM);
		}
		return Resp.ok().ok(dataRows);
	}
	
	/**
	 * 返回数据
	 * @param data
	 * @return
	 */
	public static R msgNotCheckNull(Object data) {
		return R.ok().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 删除提示
	 * @param total
	 * @param count
	 * @return
	 */
	public static R msg(Object[] total, int count) {
		if(total.length == count){
			return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}else{
			if(isIntThanZero(count)){
				return R.error(MsgConstant.removeFailed(total.length, count));
			}else{
				return R.error(MsgConstant.MSG_OPERATION_FAILED);
			}
		}
	}

	public static Resp msgResp(Object[] total, int count) {
		if(total.length == count){
			return Resp.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}else{
			if(isIntThanZero(count)){
				return Resp.error(MsgConstant.removeFailed(total.length, count));
			}else{
				return Resp.error(MsgConstant.MSG_OPERATION_FAILED);
			}
		}
	}
	
}
