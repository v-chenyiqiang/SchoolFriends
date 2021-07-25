package club.chenyiqiang.school.demo.serviceImpl;

import club.chenyiqiang.school.demo.bean.Notice;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.dao.TitDao;
import club.chenyiqiang.school.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private TitDao dao;
    @Override
    public Result getTit() {
        List<Notice> list=dao.getTit();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return Result.setSuccess(list.toString());
    }

    @Override
    public Result deleTit(int id) {
        return dao.delete(id)>0?Result.setSuccess("删除成功"):Result.setError("删除失败");
    }

    @Override
    public Result autoUpdate(List<Notice> notices) {
        for (int i=0;i<notices.size();i++){
            dao.add(notices.get(i));
        }
        return Result.setSuccess("成功");
    }
}
