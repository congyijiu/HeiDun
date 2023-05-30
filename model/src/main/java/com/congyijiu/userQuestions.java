package com.congyijiu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:46
 */
@Data
@TableName("user_questions")
public class userQuestions {
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Integer id;

    @TableField("question_id")
    private Integer questionId;

    @TableField("exam_id")
    private Integer examId;

    @TableField("correct")
    private Integer correct;
}
