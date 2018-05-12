package com.wqh.music.baidu.vo;

import lombok.Data;

@Data
public class ResultVo {
    private String code;
    private String msg;
    private Object data;

    public ResultVo() {}

    public ResultVo(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Object object)
    {
        this.code = "200";
        this.msg = "请求成功！";
        this.data = object;
    }

    public ResultVo(String code, String msg, Object object)
    {
        this.code = code;
        this.msg = msg;
        this.data = object;
    }

}
