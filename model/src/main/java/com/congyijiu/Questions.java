package com.congyijiu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.net.Inet4Address;

/**
 * @author congyijiu
 * @create 2023-05-30-23:48
 */
@Data
@TableName("Questions")
public class Questions {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("description")
    private String description;

    @TableField("options")
    private String options;

    @TableField("answer")
    private Integer answer;

    @TableField(exist = false)
    private Integer correct;
}
