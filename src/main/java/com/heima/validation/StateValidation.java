package com.heima.validation;

import com.heima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Auther: chenjia
 * @Date: 2023/12/2 - 12 - 02 - 2:01
 * @Description: com.heima.validation
 * @version: 1.0
 */
public class StateValidation implements ConstraintValidator</*给哪个注解提供校验规则*/State,
        /*校验的数据*/String> {
    /**
     *
     * @param s  将来要校验的数据
     * @param constraintValidatorContext
     *
     * @return  如果返回false，则校验不通过，如果返回ture，则校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if(s==null){
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿")){
            return true;
        }

        return false;
    }
}
