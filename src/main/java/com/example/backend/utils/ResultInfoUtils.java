package com.example.backend.utils;

import com.example.backend.entity.ResultInfo;
import org.springframework.stereotype.Component;

@Component
public class ResultInfoUtils {

    public static ResultInfo success() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setMessage("请求成功");
        return resultInfo;
    }

    public static ResultInfo success(Object data) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(200);
        resultInfo.setData(data);
        resultInfo.setMessage("请求成功");
        return resultInfo;
    }

    public static ResultInfo error(int code, String message) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(message);
        return resultInfo;
    }
}
