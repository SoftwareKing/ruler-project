package com.ylzinfo.ruler.engine;

import com.ylzinfo.ruler.core.AbstractRule;
import com.ylzinfo.ruler.factory.RuleFactory;

import java.util.Collection;

/**
 * 完整执行的规则引擎
 *
 * @param <E> 规则约束的参数类型
 * @author dengluwei
 */
public class CompleteRulesEngine<E> extends DetailRulesEngine<E> {

    public CompleteRulesEngine(RuleFactory ruleFactory, String businessType, Collection<AbstractRule<E>> abstractRules) {
        super(ruleFactory, businessType, abstractRules);
    }

    @Override
    public boolean toNext(String grade) {
        return true;
    }
}
