package com.sb.ed.po;

import java.io.Serializable;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common.po
 * @email: cy880708@163.com
 * @date: 2018/11/14 下午8:12
 * @mofified By:
 */
public class Company implements Serializable {

    private Integer cId;

    private String cCode;

    private String cName;

    private String cDes;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }
}
