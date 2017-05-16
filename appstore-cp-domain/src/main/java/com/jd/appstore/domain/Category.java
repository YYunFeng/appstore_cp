package com.jd.appstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-7-5
 * Time: 上午10:13
 * To change this template use File | Settings | File Templates.
 * 应用类目表
 */
public class Category implements Serializable {
    /**
     * 类目ID
     */
    private int categoryId;
    /**
     * 类目名
     */
    private String categoryName;
    /**
     * 类目级别
     */
    private int level;
    /**
     * 父类目ID
     */
    private int parentId;
    /**
     * 是否叶子类目
     */
    private int isLeaf;
    /**
     * 发布状态
     */
    private int pubedStatus;
    /**
     * 记录创建时间
     */
    private Date created;
    /**
     * 记录修改时间
     */
    private Date modified;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLeaf() {
        return isLeaf;
    }

    public void setLeaf(int leaf) {
        isLeaf = leaf;
    }

    public int getPubedStatus() {
        return pubedStatus;
    }

    public void setPubedStatus(int pubedStatus) {
        this.pubedStatus = pubedStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
