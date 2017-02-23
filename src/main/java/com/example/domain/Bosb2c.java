package com.example.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liuxin on 17/1/10.
 */
@XmlRootElement(name = "BOSB2C")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bosb2c {
    private String retCode;
    private String errMsg;
    private opResult opResult;
    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public opResult getOpResult() {
        return opResult;
    }

    public void setOpResult(opResult opResult) {
        this.opResult = opResult;
    }
}
