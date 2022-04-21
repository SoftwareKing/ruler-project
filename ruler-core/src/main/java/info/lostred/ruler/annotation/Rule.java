package info.lostred.ruler.annotation;

import info.lostred.ruler.constant.Grade;

import java.lang.annotation.*;

/**
 * 规则注解
 *
 * @author lostred
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Rule {
    /**
     * 规则编号
     *
     * @return 规则编号
     */
    String ruleCode();

    /**
     * 业务类型
     *
     * @return 业务类型
     */
    String businessType();

    /**
     * 规则的严重等级
     *
     * @return 规则的严重等级
     */
    Grade grade() default Grade.ILLEGAL;

    /**
     * 规则描述
     *
     * @return 规则描述
     */
    String desc();

    /**
     * 规则执行的顺序号
     *
     * @return 规则执行的顺序号
     */
    int seq() default 0;
}
