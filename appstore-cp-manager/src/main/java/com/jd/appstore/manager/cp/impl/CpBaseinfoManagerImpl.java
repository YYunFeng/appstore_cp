package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.CpBaseinfoDao;
import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.constant.SequenceConstants;
import com.jd.appstore.manager.cp.CpBaseinfoManager;
import com.jd.common.manager.BaseManager;
import com.jd.digital.common.util.cache.CacheUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class CpBaseinfoManagerImpl extends BaseManager implements CpBaseinfoManager {


    private CacheUtils cacheUtils;
    private final static Log log = LogFactory.getLog(CpBaseinfoManagerImpl.class);
    private CpBaseinfoDao cpBaseinfoDao;

    /**
     * 插入数据
     *
     * @param cpBaseinfo
     * @return
     */
    public int createCpBaseinfo(final CpBaseinfo cpBaseinfo) {
        TransactionTemplate template = getDataSourceTransactionManager();
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {

                    int id = (int) sequenceUtil.get(SequenceConstants.CP_BASEINFO_SEQUENCE);
                    cpBaseinfo.setId(id);
                    cpBaseinfoDao.createCpBaseinfo(cpBaseinfo);
                } catch (Exception e) {
                    log.error("createCpBaseinfo error!", e);
                    status.setRollbackOnly();
                }
            }
        });

        return cpBaseinfo.getId();
    }

    /**
     * 验证登录
     *
     * @param cpBaseinfo
     * @return
     */
    public CpBaseinfo findCpBaseinfo(CpBaseinfo cpBaseinfo) {
        return cpBaseinfoDao.findCpBaseinfo(cpBaseinfo);
    }

    public boolean findCpBaseinfoByPin(String userPin) {
        return cpBaseinfoDao.findCpBaseinfoByPin(userPin);
    }

    public Integer findCpAccoutId(String account) {
        return cpBaseinfoDao.findCpAccoutId(account);
    }


    public CpBaseinfoDao getCpBaseinfoDao() {
        return cpBaseinfoDao;
    }

    public void setCpBaseinfoDao(CpBaseinfoDao cpBaseinfoDao) {
        this.cpBaseinfoDao = cpBaseinfoDao;
    }

    public void setCacheUtils(CacheUtils cacheUtils) {
        this.cacheUtils = cacheUtils;
    }
}
