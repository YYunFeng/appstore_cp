package com.jd.appstore.manager.cp.impl;

import com.jd.appstore.dao.cp.CpReginfoDao;
import com.jd.appstore.domain.CpReginfo;
import com.jd.appstore.domain.constant.SequenceConstants;
import com.jd.appstore.manager.cp.CpReginfoManager;
import com.jd.common.manager.BaseManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-9
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public class CpReginfoManagerImpl extends BaseManager implements CpReginfoManager {
    private final static Log log = LogFactory.getLog(CpBaseinfoManagerImpl.class);
    private CpReginfoDao cpReginfoDao;

    public CpReginfoDao getCpReginfoDao() {
        return cpReginfoDao;
    }

    public void setCpReginfoDao(CpReginfoDao cpReginfoDao) {
        this.cpReginfoDao = cpReginfoDao;
    }

    public int createReginfo(final CpReginfo cpReginfo) {
        TransactionTemplate template = getDataSourceTransactionManager();
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    int id = (int) sequenceUtil.get(SequenceConstants.CP_REGINFO_SEQUENCE);
                    cpReginfo.setId(id);
                    cpReginfoDao.createCpReginfoDao(cpReginfo);
                } catch (Exception e) {
                    log.error("createCpBaseinfo error!", e);
                    status.setRollbackOnly();
                    throw new RuntimeException("System error!", e);
                }
            }
        });
        return cpReginfo.getId();
    }
}
