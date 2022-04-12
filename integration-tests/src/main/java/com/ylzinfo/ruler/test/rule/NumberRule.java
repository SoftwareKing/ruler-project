package com.ylzinfo.ruler.test.rule;

import com.ylzinfo.ruler.annotation.Rule;
import com.ylzinfo.ruler.core.AbstractRule;
import com.ylzinfo.ruler.core.GlobalConfiguration;
import com.ylzinfo.ruler.domain.Report;
import com.ylzinfo.ruler.domain.RuleInfo;
import com.ylzinfo.ruler.test.domain.model.ValidClass;

@Rule(ruleCode = "test_1", desc = "number必须>0", validClass = ValidClass.class)
public class NumberRule extends AbstractRule<ValidClass> {

    private final static String FIELD_NAME = "number";

    public NumberRule(GlobalConfiguration config, RuleInfo ruleInfo) {
        super(config, ruleInfo);
    }

    @Override
    public boolean isSupported(ValidClass element) {
        return element.getNumber() != null;
    }

    @Override
    public boolean judge(ValidClass element) {
        return element.getNumber().intValue() <= 0;
    }

    @Override
    public Report buildReport(ValidClass element) {
        if (this.judge(element)) {
            return this.getReport(ruleInfo, element, FIELD_NAME, element.getNumber());
        }
        return null;
    }
}
