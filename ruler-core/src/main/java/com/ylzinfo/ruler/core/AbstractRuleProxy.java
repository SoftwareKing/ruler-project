package com.ylzinfo.ruler.core;

import com.ylzinfo.ruler.constants.ValidGrade;
import com.ylzinfo.ruler.domain.Report;
import com.ylzinfo.ruler.domain.RuleInfo;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 抽象规则代理
 *
 * @author dengluwei
 */
public class AbstractRuleProxy implements MethodInterceptor {
    /**
     * 代理目标对象
     */
    private final AbstractRule<?> target;
    /**
     * 日志
     */
    private final Logger logger;

    public AbstractRuleProxy(AbstractRule<?> abstractRule) {
        this.target = abstractRule;
        this.logger = Logger.getLogger(target.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    public <T> T newProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create(new Class[]{GlobalConfiguration.class, RuleInfo.class}, new Object[]{null, null});
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invoke(target, args);
        if ("buildReport".equals(method.getName()) && result instanceof Report) {
            Report report = (Report) result;
            Map<String, Object> illegals = report.getIllegals();
            if (illegals == null || illegals.isEmpty()) {
                logger.config("grade=" + ValidGrade.QUALIFIED.getText() + ", report=" + illegals);
            } else {
                logger.config("grade=" + report.getRuleInfo().getGrade() + ", report=" + illegals);
            }
        } else if ("judge".equals(method.getName()) && result instanceof Boolean) {
            if ((Boolean) result) {
                logger.config("grade=" + target.getRuleInfo().getGrade());
            } else {
                logger.config("grade=" + ValidGrade.QUALIFIED.getText());
            }
        }
        return result;
    }
}
