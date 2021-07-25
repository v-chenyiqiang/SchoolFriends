package club.chenyiqiang.school.demo.filter;


import club.chenyiqiang.school.demo.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        User user= (User) session.getAttribute("user");
        if (url.indexOf("/yz/rec")==-1){
            System.out.println(url);
        }
        if (url.indexOf("chat.html")!=-1){
            if(user==null){
                String qNum=request.getParameter("qNum");
                if(qNum==null){
                    qNum="123456789";
                }
                session.setAttribute("addr",url+"?qNum="+qNum);
                response.sendRedirect(request.getContextPath()+"/login.html");
            }
            else{
                String qNum=request.getParameter("qNum");
                String acc=user.getAccount();
                String name=user.getName();
                System.out.println("账号是:"+acc);
                Cookie cookie = new Cookie("account",acc);
                response.addCookie(cookie);
                cookie = new Cookie("name",name);
                response.addCookie(cookie);
                cookie = new Cookie("qNum",qNum);
                response.addCookie(cookie);

            }
        }
        filterChain.doFilter(request, response);


    }
}
