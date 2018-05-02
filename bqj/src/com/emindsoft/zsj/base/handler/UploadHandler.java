package com.emindsoft.zsj.base.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ym on 14-11-28.
 */
public class UploadHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {
        if(target!=null && target.startsWith("/upload")){
            isHandled[0] = false;
        }else{
            nextHandler.handle(target, request, response, isHandled);
        }
    }

}
