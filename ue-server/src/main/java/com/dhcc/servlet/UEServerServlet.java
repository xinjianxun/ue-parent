package com.dhcc.servlet;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.ActionMap;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UEServerServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String ueditorConfigPath = config.getInitParameter("ueditorConfigPath");
        req.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type", "text/html");
        PrintWriter out = resp.getWriter();
        String actionType = req.getParameter("action");
        String result = new ActionEnter(req, ueditorConfigPath).exec();
        int actionCode = ActionMap.getType(actionType);
        if(actionCode==ActionMap.LIST_IMAGE || actionCode==ActionMap.LIST_IMAGE) {
            String replaceStr = ueditorConfigPath;
            if (replaceStr.startsWith("/")) {
                replaceStr = replaceStr.substring(1);
            }
            replaceStr = replaceStr.replace("\\", "/");
            result = result.replaceAll(replaceStr,"/");
        }
          //System.out.println(result);
        out.write(result);

    }
}
