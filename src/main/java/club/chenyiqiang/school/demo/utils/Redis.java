package club.chenyiqiang.school.demo.utils;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class Redis {
    static Jedis jedis = new Jedis("localhost");
    public static void add_yzm(String acc,String email){
        if(getValue(acc+"_yzm")!=null){
            jedis.del(acc+"_yzm");
        }
        Random random=new Random();
        StringBuilder yzm=new StringBuilder();
        for (int i=0;i<6;i++){
            yzm.append(random.nextInt(9));
        }
        acc=acc+"_yzm";
        System.out.println(yzm.toString());
        Email.send_yzm(email,"NIT密码忘记",yzm.toString());
        jedis.append(acc,yzm.toString());
        jedis.expire(acc,60);
    }
    public static boolean yz_yzm(String acc,String yzm){
        String get_yzm=getValue(acc+"_yzm");
        boolean is=yzm.equals(get_yzm);
        if(is){
            jedis.del(acc+"_yzm");
        }
        return is;
    }
    public static String getValue(String key){
        return jedis.get(key);
    }

}
