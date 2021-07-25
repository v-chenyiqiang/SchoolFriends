package club.chenyiqiang.school.demo.control;

import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.service.QunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
public class TestFile {
    @Autowired
    private QunService service;
    //单文件上传
    @RequestMapping(value = "/yz_update", method = {RequestMethod.POST})
    @ResponseBody
    public Result upload(MultipartFile[] files,String acc,long qNum) throws FileNotFoundException {
        //文件信息获取
        int num=files.length;
        System.out.println("文件数目："+num);
        int n=0;
        String path=ResourceUtils.getURL("classpath:").getPath().replace("%20"," ");
        long time=new Date().getTime();
        String dateDir =path+"chatImg/"+qNum;
        File destDir = new File(dateDir);// /xxx/upload/20191220
        System.out.println(destDir.getAbsolutePath());
        if(!destDir.exists()) {
            destDir.mkdirs();
        }
        String str="";
        for (MultipartFile file:files){
            n++;

            String fileName = file.getOriginalFilename();
            long size = file.getSize()/1024; //单位 B>KB
            String contentType = file.getContentType();
            String fileType=fileName.replaceAll(".*\\.","");
//            boolean isImag=false;
//            if(fileType.equals("jpg")||fileType.equals("png")){
//                isImag=true;
//            }
//            if(!isImag)
//                return Result.setError("格式错误");
//            if(size>1024*50){
//                return Result.setError("只支持50M以下的图片");
//            }
            //目录生成与新文件名
            String newFileName =time+"_"+n+"."+fileType;

            //文件写入
            try {
                file.transferTo(new File(destDir, newFileName));
            } catch (IllegalStateException | IOException e) {
                System.out.println(e.toString());
                return Result.setError("异常:失败");
            }
            str+=fileName+"\n\r";
        }
        return service.sendFileByUser(acc,qNum,str,files.length,time);
    }
    @RequestMapping("/yz_download")
    @ResponseBody
    public void downloadFile(String qNum,int num,long time,String oldname,HttpServletResponse response) throws FileNotFoundException {
        String path=ResourceUtils.getURL("classpath:").getPath().replace("%20"," ");
        String fileName=time+"_"+num;
        File ml=new File(path+"chatImg/"+qNum+"/");
        if (!ml.exists()){
            return ;
        }
        File[] mls=ml.listFiles();
        File file =null;
        for (File m:mls){
            System.out.println(m.getName()+" ----  "+fileName);
            if(m.getName().indexOf(fileName)!=-1){
                file=m;
                break;
            }
        }

            if (file!=null) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" +oldname);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }

                    os.close();
                    System.out.println("下载成功");
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        System.out.println("下载失败");
        return ;
    }
}
