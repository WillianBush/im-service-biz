package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.entity.*;
import net.chenlin.dp.common.support.properties.GlobalProperties;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.IdGenerate;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;
import net.chenlin.dp.modules.biz.member.dao.FriendsMapper;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.room.dao.MessageHistoryMapper;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.service.MemberService;

/**
 * @author wang<fangyuan.co @ outlook.com>
 */
@Slf4j
@Service("memberService")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    private OSSModel ossModel;

    private EmployeeService employeeService;

    private MessageHistoryMapper messageHistoryMapper;

    private RedisCacheManager redisCacheManager;

    private DomainsService domainsService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<MemberEntity> listMember(Map<String, Object> params) {
        DomainsEntity domainsEntity=domainsService.getDomainsByUrl(params.get("domain").toString());

        if (null != params && !params.isEmpty()) {
            List<String> lastLogin = (ArrayList<String>) params.get("createdate");
            if (lastLogin != null && lastLogin.size() == 2) {
                params.put("createdateStart", lastLogin.get(0));
                params.put("createdateEnd", lastLogin.get(1));
            }
        }

        params.put("org_id",domainsEntity.getOrg_id());
        Query query = new Query(params);
        Page<MemberEntity> page = new Page<>(query);
        List<MemberEntity> list = memberMapper.listForPage(page, query);
        for (MemberEntity m : list) {
            if (StringUtils.isEmpty(m.getHeadpic())) {
                m.setHeadpic("https://" + ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
            } else {
                m.setHeadpic("https://" + ossModel.getEndpoint() + m.getHeadpic());
            }
        }
        page.setRows(list);
        return page;
    }

    /**
     * 新增
     *
     * @param member
     * @return
     */
    @Override
    public Resp saveMember(MemberEntity member,String domain) {
        SnowFlakeIdWorker sw = new SnowFlakeIdWorker(1);
        //根据username查询是否存在
        Map<String, Object> para = new HashMap<>();
        para.put("username", member.getUsername());
        Page<MemberEntity> rsPage = listMember(para);
        if (rsPage.getRows().size() > 0) {
            return Resp.error(Resp.error, "用户已经存在");
        }
        long number = redisCacheManager.incr(RedisCacheKeys.REDIS_KEY_CREATE_MEMBERID, 1);
        member.setMemberid(Long.toString(number));
        member.setUsername(RedisCacheKeys.REDIS_KEY_CREATE_USERNAME + number);
        member.setTelphone(RedisCacheKeys.default_telphone);
        //普通用户
        member.setMembertype(0);
        member.setPassword(MD5Utils.MD5Encode(member.getPassword()));
        member.setId(IdGenerate.generateUUID());
        member.setStatus(0);
        int count = memberMapper.save(member);

		if (member.getIs_employee().equals(1)) {
			EmployeeEntity employee = new EmployeeEntity();
			employeeService.saveEmployee(employee, this.getMemberById(member.getId()).getData(),domain);
		}

        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp batchSaveMember(List<MemberEntity> members,String domain) {
        log.info("批量添加用户---startcheck");
        for (MemberEntity memberEntity : members) {
            if (StringUtils.isEmpty(memberEntity.getPassword())) {
                return Resp.error(500, "密码不能为空");
            }
            if (StringUtils.isEmpty(memberEntity.getNickname()) || memberEntity.getNickname().contains(" ")) {
                return Resp.error(500, "昵称不呢为空且不能含有空格" + memberEntity.getNickname());
            }
            log.info("批量添加用户---校验用户是否存在");
            if (memberMapper.isExitByNickname(memberEntity.getNickname()) > 0L) {
                log.info("批量添加用户---昵称重复");
                return Resp.error(500, "昵称重复");
            }

        }
        members.forEach(memberEntity -> {
            saveMember(memberEntity,domain);
        });
        return Resp.ok();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Resp<MemberEntity> getMemberById(String id) {
        MemberEntity member = memberMapper.getObjectById(id);
        return CommonUtils.msgResp(member);
    }

    @Override
    public MemberEntity getByUserName(String username) {
        return memberMapper.getByUsername(username);
    }

    /**
     * 根据mid查询
     *
     * @param memberId
     * @return
     */
    @Override
    public Resp<MemberEntity> getMemberByMid(String memberId) {
        MemberEntity member = memberMapper.getMemberByMid(memberId);
        if (member == null) {
            return Resp.error("用户不存在！");
        } else {
            return CommonUtils.msgResp(member);
        }
    }


    /**
     * 修改
     *
     * @param member
     * @return
     */
    @Override
    public Resp updateMember(MemberEntity member) {
        int count = memberMapper.update(member);
        return CommonUtils.msgResp(count);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Resp batchRemove(String[] id) {
        int count = memberMapper.batchRemove(id);
        return CommonUtils.msgResp(id, count);
    }

    /**
     * 根据用户ID删除所有的聊天记录
     *
     * @return
     */
    @Override
    public Resp removeAllHistoryMsgByUid(String uid) {
        messageHistoryMapper.deleteByFromUid(uid);
        return Resp.ok();
    }

    @Override
    public Resp updateMemberPass(MemberEntity member) {
        MemberEntity me = new MemberEntity();
        me.setId(member.getId());
        me.setPassword(MD5Utils.MD5Encode(member.getPassword()));
        int count = memberMapper.update(me);
        return CommonUtils.msgResp(count);
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<MemberEntity> listFriends(Map<String, Object> params) {
        DomainsEntity domainsEntity=domainsService.getDomainsByUrl(params.get("domain").toString());
        params.put("org_id", domainsEntity.getOrg_id());
        Query query = new Query(params);
        Page<MemberEntity> page = new Page<>(query);
        page.setRows(memberMapper.listForPageByFriend(page, query));
        return page;
    }


    @Override
    public Resp<Long> getTotalNumber(String domain) {
        DomainsEntity domainsEntity=domainsService.getDomainsByUrl(domain);
        if (null == domainsEntity){
            log.error("domain 无法获取对应的 org_id, domain:{}",domain);
            return CommonUtils.msgResp(-1L);
        }
        return CommonUtils.msgResp(memberMapper.getTotal(domainsEntity.getOrg_id()));
    }
}
