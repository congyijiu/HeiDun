package com.congyijiu.Dto;

import com.congyijiu.Questions;
import com.congyijiu.UserExams;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-06-01-22:50
 */
@Data
public class UserExamsDto {
    private UserExams userExams;

    private Questions questions;
}
