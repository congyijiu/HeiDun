package com.congyijiu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author congyijiu
 * @create 2023-05-30-23:41
 */
@Data
@TableName("Exams")
public class Exams {
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("subject")
    private String subject;

    @TableField("exams_time")
    private String examsTime;

    @TableField("num_questions")
    private Integer numQuestions;

    @TableField("status")
    private Integer status;

    @TableField("score")
    private Integer score;

    @TableField("user_id")
    private Long userId;

    @TableField("result")
    private String result;

    @TableField("type")
    private Integer type;

}
