package com.ylzinfo.ruler.rule;

import com.ylzinfo.ruler.core.ValidConfiguration;
import com.ylzinfo.ruler.domain.Report;
import com.ylzinfo.ruler.domain.RuleInfo;
import com.ylzinfo.ruler.domain.ValidInfo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 必填字段校验规则
 *
 * @param <T> 规则约束的参数类型
 * @author dengluwei
 */
public class ScopeFieldRule<T> extends SingleFieldValidRule<T> {

    public ScopeFieldRule(ValidConfiguration validConfiguration, RuleInfo ruleInfo) {
        super(validConfiguration, ruleInfo);
    }

    @Override
    public boolean isSupported(T element) {
        return !validConfiguration.getScopeValidInfos().isEmpty();
    }

    @Override
    public boolean judge(T element) {
        return validConfiguration.getScopeValidInfos().values().stream()
                .anyMatch(validInfo -> this.check(element, validInfo));
    }

    @Override
    public Report buildReport(T element) {
        Map<String, Object> map = validConfiguration.getScopeValidInfos().values().stream()
                .flatMap(validInfo -> this.collectIllegal(element, validInfo).stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return this.getReport(this.ruleInfo, map);
    }

    @Override
    protected boolean match(String fieldName, Object value) {
        Map<String, ValidInfo> map = validConfiguration.getScopeValidInfos();
        if (map != null && value != null && !"".equals(value)) {
            ValidInfo validInfo = map.get(fieldName);
            if (value instanceof Number) {
                BigDecimal bigDecimal = new BigDecimal(value.toString());
                BigDecimal lowerLimit = validInfo.getLowerLimit();
                BigDecimal upperLimit = validInfo.getUpperLimit();
                if (lowerLimit != null) {
                    return bigDecimal.compareTo(lowerLimit) < 0;
                }
                if (upperLimit != null) {
                    return bigDecimal.compareTo(upperLimit) > 0;
                }
            }
        }
        return false;
    }

    @Override
    protected Set<Map.Entry<String, Object>> collectToSet(Object validNode, String fieldName, Object value) {
        Map<String, ValidInfo> map = validConfiguration.getScopeValidInfos();
        ValidInfo validInfo = map.get(fieldName);
        BigDecimal lowerLimit = validInfo.getLowerLimit();
        BigDecimal upperLimit = validInfo.getUpperLimit();
        String lower = lowerLimit == null ? "-∞" : lowerLimit.toString();
        String upper = upperLimit == null ? "+∞" : upperLimit.toString();
        value = value + " (参考值: " + lower + " ~ " + upper + ")";
        return this.transToSet(validNode, fieldName, value);
    }
}
