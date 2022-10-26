package net.chenlin.dp.modules.biz.bussiness.entity;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import java.util.UUID;

@Data
public class ChatMsgEntity implements Cloneable {

	protected String uuid;
	protected String fromUid;
	protected String fromHeadpic;
	protected String fromName;
	protected String date;
	protected Long dateTime;
	protected String toUid;
	protected String toGroupid;
	protected String simple_content;//用于前端消息列表最后的消息
	protected String chatid="";
	protected Integer read = 0;//-1未发送成功【一般在发送消息时设置】 0未读 1已读
	private String txt;
	private String oldTxt;//也就是TXT未处理前的字符
	private String sub_txt;//特殊情况下使用 如语音需要记录多少秒
	private String psr="";//解析方式  默认使用rich-text   还有其它的选择uparse voice(语音) video(视频) url(超链接)
	private String aite="";//@群成员 多个ID用#分开
}
