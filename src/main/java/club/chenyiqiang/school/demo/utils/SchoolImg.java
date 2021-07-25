package club.chenyiqiang.school.demo.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchoolImg {
    public List<String> getList(){
        String url="http://www.nit.edu.cn/nggk/xyfj.htm";
        Document doc= null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list=new ArrayList<String>();
        Element element=doc.select("#imgs").first();
        List<Element> els=element.select("img");
        for (int i=0;i<els.size();i++){
            list.add(els.get(i).attr("abs:src"));
        }
        return list;
    }

}
