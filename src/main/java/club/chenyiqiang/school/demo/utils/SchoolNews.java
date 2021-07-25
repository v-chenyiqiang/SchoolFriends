package club.chenyiqiang.school.demo.utils;

import club.chenyiqiang.school.demo.bean.Notice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchoolNews {
    public List<Notice> getList(){
        String url="http://www.nit.edu.cn/ngxw/xxyw.htm";
        Document doc= null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list=new ArrayList<String>();
        Element element=doc.select(".midright").first();
      //  System.out.println(element);
        List<Element> els=element.select("tr");
        List<Notice> notices=new ArrayList<>();
        for (int i=0;i<els.size();i++){
           // list.add(els.get(i).attr("abs:src"));
            if(els.get(i).attr("id").indexOf("line")!=-1){
                Notice notice=new Notice();
                notice.setTime(els.get(i).select("td").get(2).select("span").first().text());
                notice.setUrl(els.get(i).select("td").get(1).select("a").first().attr("abs:href"));
                notice.setTit(els.get(i).select("td").get(1).select("a").first().attr("title"));
                notices.add(notice);
            }
           // System.out.println(els.get(i));
        }
        return notices;
    }

}
