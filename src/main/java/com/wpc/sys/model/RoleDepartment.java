/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.model;

import com.wpc.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 功能描述: 角色-机构
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public class RoleDepartment extends BaseEntity {

	// 角色编号
	private Long roleId;
	// 机构编号
	private Long departmentId;
		
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public Long getDepartmentId() {
		return this.departmentId;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("RoleDepartment[");
		sb.append("roleId=");
		sb.append(roleId);
		sb.append(", departmentId=");
		sb.append(departmentId);
		sb.append("]");
		return sb.toString();
	}
}
