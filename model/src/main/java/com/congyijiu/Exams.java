package com.congyijiu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author congyijiu
 * @create 2023-05-30-23:41
 */
@Data
@ApiModel(value="考试对象", description="")
@TableName("Exams")
public class Exams {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "考试名称")
    @TableField("subject")
    private String subject;

    @ApiModelProperty(value = "考试时间")
    @TableField("exams_time")
    private String examsTime;

    @ApiModelProperty(value = "题目数量")
    @TableField("num_questions")
    private Integer numQuestions;


    @ApiModelProperty(value = "考试状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "考试分数")
    @TableField("score")
    private Integer score;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "考试结果")
    @TableField("result")
    private String result;

    @ApiModelProperty(value = "考试类型")
    @TableField("type")
    private Integer type;

}
