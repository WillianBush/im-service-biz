package net.chenlin.dp.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统级静态变量
 * @author wang<fangyuan.co@outlook.com>
 */
public class SystemConstant {
	
	/**
	 * 超级管理员ID
	 */
	public static final long SUPER_ADMIN = 1;
	
	/**
	 * 数据标识
	 */
	public static final String DATA_ROWS = "rows";


	public static Map<Integer,String> DEVICE = new HashMap<>();

	static {
		DEVICE.put(0,"PC");
		DEVICE.put(1,"android");
		DEVICE.put(2,"ios");
	}
	
	/**
	 * 菜单类型
	 *
	 * @author ZhouChenglin
	 * @email: fangyuan.co@outlook.com
	 * @url: www.chenlintech.com
	 * @date 2017年8月8日 下午1:36:27
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private final int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     *
     * @author ZhouChenglin
     * @email: fangyuan.co@outlook.com
     * @url: www.chenlintech.com
     * @date 2017年8月8日 下午1:36:17
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(1),
        /**
         * 暂停
         */
    	PAUSE(0);

        private final int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    /**
     * 通用字典
     *
     * @author ZhouChenglin
     * @email fangyuan.co@outlook.com
     * @url www.chenlintech.com
     * @date 2017年8月15日 下午7:29:02
     */
    public enum MacroType {
    	
    	/**
    	 * 类型
    	 */
    	TYPE(0),
    	
    	/**
    	 * 参数
    	 */
    	PARAM(1);
    	
    	private final int value;
    	
    	MacroType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }
    
    /**
     * 通用变量，表示可用、禁用、显示、隐藏
     *
     * @author ZhouChenglin
     * @email fangyuan.co@outlook.com
     * @url www.chenlintech.com
     * @date 2017年8月15日 下午7:31:49
     */
    public enum StatusType {
    	
    	/**
    	 * 禁用，隐藏
    	 */
    	DISABLE(0),
    	
    	/**
    	 * 可用，显示
    	 */
    	ENABLE(1),
    	
    	/**
    	 * 显示
    	 */
    	SHOW(1),
    	
    	/**
    	 * 隐藏
    	 */
    	HIDDEN(0);
    	
    	private final int value;
    	
    	StatusType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }

}
