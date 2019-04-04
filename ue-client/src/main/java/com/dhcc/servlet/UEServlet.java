package com.dhcc.servlet;


import com.dhcc.util.ActionMap;
import com.dhcc.util.HttpClientResult;
import com.dhcc.util.HttpClientUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

public class UEServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String ueditorServerRemoatUrl = config.getInitParameter("ueditorServerRemoteUrl");

        String url = ueditorServerRemoatUrl;

        String method = req.getMethod();

        String content = "";

        String actionType = req.getParameter("action");

        int actionCode = ActionMap.getType(actionType);

        switch (actionCode) {
            case ActionMap.CONFIG:
            case ActionMap.CATCH_IMAGE:
            case ActionMap.LIST_IMAGE:
            case ActionMap.LIST_FILE:
                content = this.doGetRequest(req,url);
                break;
            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                content = this.doUpload(req,url);
                break;
        }

            PrintWriter out = resp.getWriter();
            out.write(content);

        }

    private String  doGetRequest(HttpServletRequest req,String url) {
        String content = "";
        try {
            Map<String, String> paramsMap = HttpClientUtils.generateParamsMapFromRequest(req);
            HttpClientResult result = HttpClientUtils.doGet(url, paramsMap);
             content = result.getContent();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    private String doUpload(HttpServletRequest req,String url) {
        String content = "";
        try{
            Enumeration<String> enumeration = req.getParameterNames();
            url += "?";
            while (enumeration.hasMoreElements()) {
                String nex = enumeration.nextElement();
                url += nex+"="+req.getParameter(nex)+"&";
            }
            HttpClientResult result = HttpClientUtils.doPostMultiPart(url,null, req);
            content = result.getContent();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
