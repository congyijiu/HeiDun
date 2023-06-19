package com.congyijiu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:46
 */

@Data
@ApiModel(value="用户考试对象", description="")
@TableName("user_exams")
public class UserExams {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户考试id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "题目id")
    @TableField("question_id")
    private Long questionId;

    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private Long examId;

    @ApiModelProperty(value = "正确情况")
    @TableField("correct")
    private Integer correct;

    @ApiModelProperty(value = "用户选择")
    @TableField("selected")
    private Integer selected;
}
