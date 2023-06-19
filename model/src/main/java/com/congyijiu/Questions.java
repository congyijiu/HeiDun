package com.congyijiu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.net.Inet4Address;

/**
 * @author congyijiu
 * @create 2023-05-30-23:48
 */

@Data
@ApiModel(value="题目对象", description="")
@TableName("Questions")
public class Questions {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "题目描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "题目选项")
    @TableField("options")
    private String options;

    @ApiModelProperty(value = "题目答案")
    @TableField("answer")
    private Integer answer;

    @ApiModelProperty(value = "正确情况")
    @TableField(exist = false)
    private Integer correct;

    @ApiModelProperty(value = "题目解析")
    @TableField("explanation")
    private String explanation;
}
