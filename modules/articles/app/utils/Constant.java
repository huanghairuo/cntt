package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Play;

public class Constant {
    /** 数据分隔符 */
    public final static String SEPARATOR = "|";
    /** 数据库用户名 */
    public static String DB_NAME = null;
    /** 同步类包路径 */
    public final static String PACKAGE_NAME = "models.sync";
    
    /**
     * <li>0-初始状态</li>
     * <li>1-驳回</li>
     * <li>2-业务审批</li>
     * <li>3-IT确认</li>
     * <li>4-领导审批</li>
     * <li>5-IT管理员操作（部署中）</li>
     * <li>6-部署完成</li>
     */
    public final static String PROJECT_STATE_INIT="0";
    public final static String PROJECT_STATE_REJECT="1";
    public final static String PROJECT_STATE_BUSINESS="2";
    public final static String PROJECT_STATE_ITCONFIRM="3";
    public final static String PROJECT_STATE_APPROVE="4";
    public final static String PROJECT_STATE_OPERATOR="5";
    public final static String PROJECT_STATE_DEPLOY="6";
    
    /**
     * 告警级别
     */
    public final static String LOG_INFO="info";
    public final static String LOG_WARNING="warning";
    public final static String LOG_ERROR="error";
    
    /**
     * 操作类型
     */
    public final static String OP_LOGGIN="loggin";
    public final static String OP_LOGOUT="logout";
    public final static String OP_REQUEST_LIMITED="req_limited";
    
    
}
