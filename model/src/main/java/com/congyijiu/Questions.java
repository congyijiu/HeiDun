package com.congyijiu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-05-30-23:48
 */
@Data
@TableName("Questions")
public class Questions {
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("description")
    private String description;

    @TableField("option")
    private String option;

    @TableField("answer")
    private Integer answer;
}
