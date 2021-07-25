package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.Imgs;
import club.chenyiqiang.school.demo.bean.Result;

public interface ImgsService {
    public Result getImgs();
    public Result addImgs(Imgs imgs);
    public Result updateID(int old,int theNew);
}
