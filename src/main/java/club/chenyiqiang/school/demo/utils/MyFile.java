package club.chenyiqiang.school.demo.utils;

import club.chenyiqiang.school.demo.bean.News;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.FileCopyUtils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFile {

    public static void main(String[] args) throws IOException {
        //C:\Users\hspcadmin\Desktop\小本本.txt
        String path="C:\\Users\\hspcadmin\\Desktop\\小本本.txt";
        File f=new File(path);
        FileInputStream inputStream=new FileInputStream(f);
        byte[] b=new byte[1024];
        inputStream.read(b);
        for (int i=0;i<b.length;i++){
            System.out.println(String.format("%08d",Integer.parseInt(Integer.toBinaryString(0xff & b[i]))));
        }
        System.out.println(inputStream);
    }
    public static File copyFile(File oldFile,File newFile) {
        try {
            FileInputStream inputStream=new FileInputStream(oldFile);
            byte[] b= new byte[1024];
            int line;
            FileOutputStream outputStream=new FileOutputStream(newFile);
            while((line=inputStream.read(b))!=-1){
                outputStream.write(b,0,line);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
        return newFile;
    }
}
