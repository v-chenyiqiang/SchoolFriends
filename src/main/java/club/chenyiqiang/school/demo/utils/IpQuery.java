package club.chenyiqiang.school.demo.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class IpQuery {

    public static void main(String[] args) {
        System.out.println(new IpQuery().dealIp("58.101.55.163"));
    }
    public  String dealIp(String ip){
        String url="https://www.ip138.com/iplookup.asp?ip="+ip+"&action=2";
        Document doc= null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str=doc.select("script").get(1).toString().trim();
        String[] sz=str.split("var");
        String re=null;
        re=sz[2].replaceAll(".*归属地|iP段.*| .*|\"|:|,","").trim();
        return re;
    }





}
