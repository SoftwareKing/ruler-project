package info.lostred.ruler.constant;

/**
 * 引擎类型
 *
 * @author lostred
 */
public enum EngineType {
    /**
     * 规则引擎将执行完所有的规则
     */
    COMPLETE,
    /**
     * 规则引擎在生成非法报告时将结束执行
     */
    INCOMPLETE,
    /**
     * 规则引擎不生成报告，只返回一个布尔值，值为true时表示违规，结果为false时表示不违规
     */
    SIMPLE
}
