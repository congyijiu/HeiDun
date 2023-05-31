package com.congyijiu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2023-05-30-23:41
 */
@Data
@TableName("Exams")
public class Exams {
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Integer id;

    @TableField("subject")
    private String subject;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("num_questions")
    private Integer numQuestions;

    @TableField("staus")
    private Integer staus;

    @TableField("score")
    private Integer score;

    @TableField("user_id")
    private Integer userId;

}
