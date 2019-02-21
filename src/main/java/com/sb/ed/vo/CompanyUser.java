package com.sb.ed.vo;

import com.sb.ed.po.Company;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common.vo、
 * @email: cy880708@163.com
 * @date: 2018/11/16 下午3:57
 * @mofified By:
 */
public class CompanyUser extends Company {

    /**
     * 用户名称
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CompanyUser{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
