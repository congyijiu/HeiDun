package com.congyijiu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:46
 */
@Data
@TableName("user_exams")
public class UserExams {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("question_id")
    private Long questionId;

    @TableField("exam_id")
    private Long examId;

    @TableField("correct")
    private Integer correct;

    @TableField("selected")
    private Integer selected;
}
