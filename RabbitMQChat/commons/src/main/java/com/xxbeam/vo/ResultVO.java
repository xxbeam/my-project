package com.xxbeam.vo;

public class ResultVO {
    private int code;
    private String desc;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static ResultVO ok(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setDesc("success");
        return resultVO;
    }

    public static ResultVO ok(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setDesc("success");
        resultVO.setObject(object);
        return resultVO;
    }
}
