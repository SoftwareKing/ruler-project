package info.lostred.ruler.core;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;

/**
 * 判断器接口
 *
 * @author lostred
 */
public interface Judgement {
    /**
     * 在给定的评估上下文与表达式解析器下，判断器接口是否支持对该参数进行判断
     *
     * @param context 评估上下文
     * @param parser  表达式解析器
     * @param object  参数
     * @return 支持返回true，否则返回false
     */
    boolean supports(EvaluationContext context, ExpressionParser parser, Object object);

    /**
     * 根据评估上下文与表达式解析器，判断参数是否满足特定的条件
     *
     * @param context 评估上下文
     * @param parser  表达式解析器
     * @param object  参数
     * @return 满足条件返回true，否则返回false
     */
    boolean judge(EvaluationContext context, ExpressionParser parser, Object object);
}
