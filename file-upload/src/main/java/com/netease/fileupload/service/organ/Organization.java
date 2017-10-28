package com.netease.fileupload.service.organ;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 组织机构实体
 *
 */
public class Organization implements Serializable,Cloneable{
    /**
     * 
     */
    private static final long serialVersionUID = -5115279179190979571L;
    /*
     * 主键id
     */
    private Integer id;
    /**
     * 机构id
     */
    private String orgCode;

    /**
     * 机构id 未加密
     */
    private String orgCodeBeforeMd5;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 父级机构id
     */
    private String parentCode;
    /**
     * 机构树
     */
    private String orgTree;
    /**
     * 创建人
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updateUserName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 工程id
     */
    private Integer projectId;
    /**
     * 删除标记
     */
    private Integer isdel;
    
    public Organization(){
        orgCodeBeforeMd5="";
    }
    
    public Organization(OrganizationVO vo){
        this.name=vo.getName();
        this.orgCode = vo.getOrgCode();
        this.parentCode=vo.getParentCode();
        this.projectId=vo.getProjectId();
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public String getOrgTree() {
        return orgTree;
    }
    public void setOrgTree(String orgTree) {
        this.orgTree = orgTree;
    }
    public String getCreateUserName() {
        return createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdateUserName() {
        return updateUserName;
    }
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getOrgCodeBeforeMd5() {
        return orgCodeBeforeMd5;
    }

    public void setOrgCodeBeforeMd5(String orgCodeBeforeMd5) {
        this.orgCodeBeforeMd5 = orgCodeBeforeMd5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", name=").append(name);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", orgTree=").append(orgTree);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserName=").append(updateUserName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", projectId=").append(projectId);
        sb.append(", isdel=").append(isdel);
        sb.append("]");
        return sb.toString();
    }
    
    @Override  
    public Organization clone() {  
    	Organization organ = null;  
        try{  
        	organ = (Organization)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return organ;  
    }  
}
