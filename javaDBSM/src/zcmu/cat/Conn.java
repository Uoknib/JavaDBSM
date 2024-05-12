package src.zcmu.cat;

public class Conn {
    public static void conn(String sql)//conn usename/passwords
    {
        int index = sql.indexOf("/");
        if(index==-1)
            System.out.println("ERROR: 语句有错误");
        else
        {
        String username = sql.substring(5, index);
        String passwords = sql.substring(index+1,sql.length()-1);
        
        //System.out.println("uName: "+username +"|"+"pWords: "+passwords);
        
        SQLUser.userIdentify(username, passwords);
        }
        Input.get();
    }
}
