package com.logictrue.system.service.impl;

import java.util.List;

import com.logictrue.common.core.constant.SecurityConstants;
import com.logictrue.common.core.exception.BaseException;
import com.logictrue.system.api.domain.SysOperLog;
import com.logictrue.wms.api.RemotePurchaseService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logictrue.system.domain.SysNotice;
import com.logictrue.system.mapper.SysNoticeMapper;
import com.logictrue.system.service.ISysNoticeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告 服务层实现
 * 
 * @author logicTrue
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Autowired
    private RemotePurchaseService purchaseService;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    @GlobalTransactional
    public int insertNotice(SysNotice notice)
    {
        //调用仓储服服务删除
        Long[] l ={new Long(4), new Long(111)};
        purchaseService.remove(l, SecurityConstants.INNER);
        boolean flag = true;
        if(flag){
            throw new BaseException("调试分布式事务");
        }
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }
}
