package com.congyijiu.Dto;

import com.congyijiu.Questions;
import lombok.Data;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-05-31-22:10
 */
@Data
public class ExamsDto {

    private Long examId;

    List<Questions> questions;
}
