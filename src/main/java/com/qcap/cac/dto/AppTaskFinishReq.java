package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AppTaskFinishReq {

    @NotBlank(message = "任务编号不能为空")
    @ApiModelProperty("任务编号")
    private String taskCode;

    @ApiModelProperty("反馈文件")
    private String feedbackImgUrl;

    @ApiModelProperty("任务完成描述")
    private String taskFinishDesc;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getFeedbackImgUrl() {
        return feedbackImgUrl;
    }

    public void setFeedbackImgUrl(String feedbackImgUrl) {
        this.feedbackImgUrl = feedbackImgUrl;
    }

    public String getTaskFinishDesc() {
        return taskFinishDesc;
    }

    public void setTaskFinishDesc(String taskFinishDesc) {
        this.taskFinishDesc = taskFinishDesc;
    }
}
