package solekia.demo.fastfood.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init!!");
        //☆☆ここにログインしていなくても開けるページ☆☆
        urls.add("^/login");
        urls.add("^/login/check");
    }

    private List<String> urls = new  ArrayList<String>();

    private boolean checkURL(String checkurl){
        boolean flag =false;
        for (String url : urls) {
            if(checkurl.matches(url)){
                flag=true;
                break;
            }
        }
        return flag;
    }
    

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String url = request.getServletPath();
                //ログイン画面に戻す処理
                if (url == null) {
                    response.sendRedirect("login");
                //セッション無しで移動できるページの指定と処理
                // } else if (url.matches("^/login") || url.matches("^/login/check")  || url.equals("/top") || url.matches("^/search") || url.matches("^/search/s") || url.matches("^/forget") || url.matches("^/forget/edit") || url.matches("^/newcreate") || url.matches("^/top") || url.matches("^/faq")|| url.matches("^/forget")|| url.matches("^/newcreate/newcheck")|| url.matches("^/newcreate/login")|| url.matches("^/newcreate/newcreate") || url.matches("^/AboutUs") || url.matches("^/newcreate/newcomplete")) {
                } else if (checkURL(url)) {
                    chain.doFilter(request, response);
                //フィルターをかけない拡張子の指定
                } else if (url.matches(".+\\.(css|png|jpg)")) {
                    chain.doFilter(request, response);
                } else {
                    HttpSession session = request.getSession(false);
                    if (null == session) {
                        response.sendRedirect("/login");
                    } else {
                        chain.doFilter(request, response);
                    }
                }
    }

    @Override
    public void destroy() {
        System.out.println("destroy!!");
    }
}