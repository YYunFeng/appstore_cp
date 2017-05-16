package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.CpBaseinfoDao;
import com.jd.appstore.dao.cp.CpReginfoDao;
import com.jd.appstore.domain.CpBaseinfo;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.domain.constant.SequenceConstants;
import com.jd.appstore.manager.cp.AddDeveloperPersonManager;
import com.jd.common.manager.BaseManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-10
 * Time: 下午7:12
 * To change this template use File | Settings | File Templates.
 */
public class AddDeveloperManagerImpl extends BaseManager implements AddDeveloperPersonManager {
    private final static Log log = LogFactory.getLog(AddDeveloperManagerImpl.class);
    private CpBaseinfoDao cpBaseinfoDao;
    private CpReginfoDao cpReginfoDao;

    public CpBaseinfoDao getCpBaseinfoDao() {
        return cpBaseinfoDao;
    }

    public void setCpBaseinfoDao(CpBaseinfoDao cpBaseinfoDao) {
        this.cpBaseinfoDao = cpBaseinfoDao;
    }

    public CpReginfoDao getCpReginfoDao() {
        return cpReginfoDao;
    }

    public void setCpReginfoDao(CpReginfoDao cpReginfoDao) {
        this.cpReginfoDao = cpReginfoDao;
    }

    public int AddDeveloperPerson(final CpBaseinfo cpBaseinfo, final CpReginfo cpReginfo) {
        TransactionTemplate template = getDataSourceTransactionManager();
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    // 判断数据库是否已经有pin用户
                    if (cpBaseinfoDao.findCpBaseinfoByPin(cpBaseinfo.getAccount())) {
                        log.error("插入用户到数据库失败，数据库已经存在此用户。pin=" + cpBaseinfo.getAccount());
                        return;
                    }
                    int cpBaseinfoId = (int) sequenceUtil.get(SequenceConstants.CP_BASEINFO_SEQUENCE);
                    cpBaseinfo.setId(cpBaseinfoId);
                    cpBaseinfo.setAccountStatus(1);
                    cpBaseinfo.setDefaultFeeMode(0);
                    cpBaseinfo.setUpdateStatus(0);
                    cpBaseinfoDao.createCpBaseinfo(cpBaseinfo);
                    int cpReginfoId = (int) sequenceUtil.get(SequenceConstants.CP_BASEINFO_SEQUENCE);
                    cpReginfo.setId(cpReginfoId);
                    cpReginfo.setAccountId(cpBaseinfoId);
                    cpReginfo.setCopy(1);
                    cpReginfoDao.createCpReginfoDao(cpReginfo);
                } catch (Exception e) {
                    log.error("createCpBaseinfo error!", e);
                    status.setRollbackOnly();
                    throw new RuntimeException("System error!", e);
                }
            }
        });

        return 1;

    }
}
