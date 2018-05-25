package com.emmessess.retrofitmultipartwithbodyparams;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sadiq on 05/25/18.
 */

public class AssignmentUpload {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("session_status")
    @Expose
    private Boolean sessionStatus;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(Boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}