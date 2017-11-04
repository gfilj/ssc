package com.project.common.exception;

/**
 * Created by goforit on 2017/10/22.
 */
public enum ExceptionEnum {


    SERVLET_CAUSE(1001, "获取servlet异常"),
    IOEXCEPTION_CAUSE(1002, "IO Exception错误"),
    FILE_NOT_FOUND_CAUSE(1003, "无法找到文件"),
    DATA_CAUSE(1004, "数据存储异常"),
    XML_PARSE_CAUSE(1005, "XML解析错误"),
    ENCODE_UNSURRPOT_CAUSE(1006,"不支持此编码"),

    MD5_ALGORITHM_CAUSE(2001, "算法检查：无此算法"),
    MD5_CHECK_ERROR_CAUSE(2002, "md5 校验失败"),

    ARGUMENT_NULL_CAUSE(3001, "传入参数为空"),
    ARGUMENT_ILLEGAL_CAUSE(3002, "传入参数非法"),
    ARGUMENT_DEFINE_CAUSE(3003, "传入参数无此定义"),

    WECHAT_REQUEST_ERROR(4001, "请求出错");


    private int type;
    private String description;

    ExceptionEnum(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
