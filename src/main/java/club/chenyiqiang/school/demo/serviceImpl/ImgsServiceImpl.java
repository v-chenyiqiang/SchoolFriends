package club.chenyiqiang.school.demo.serviceImpl;

import club.chenyiqiang.school.demo.bean.Imgs;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.dao.ImgsDao;
import club.chenyiqiang.school.demo.service.ImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ImgsServiceImpl  implements ImgsService {
    @Autowired
    private ImgsDao dao;
    @Override
    public Result getImgs() {
        return Result.setSuccess(dao.getImgs().toString());
    }

    @Override
    public Result addImgs(Imgs imgs) {
        int code=dao.isHas(imgs.getUrl());
        if(code==1){
            return Result.setError("已经存在");
        }
        int id=dao.getID();
        id++;
        imgs.setId(id);
        return dao.addImg(imgs)>0?Result.setSuccess("成功"):Result.setError("失败");
    }

    @Override
    public Result updateID(int old, int theNew) {
        int has=1;
        Random r=new Random();
        int sj=0;
        while (has==0) {
            sj = r.nextInt(20000) + 10000;
            has = dao.isHasById(sj);
        }
        dao.updateId(theNew,sj);
        dao.updateId(old,theNew);
        dao.updateId(sj,old);
        return Result.setSuccess("成功");
    }
}
