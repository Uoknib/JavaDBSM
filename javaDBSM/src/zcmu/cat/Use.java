package src.zcmu.cat;
import java.util.List;
//finished
public class Use {
    public static void useSql(String sql)
    {
        String dbName = sql.substring(4,sql.length()-1);
        String path = SQLConstant.getPath();
        List<String> dbList = Tools.getAllDatabase(path);
        boolean flag = dbList.contains(dbName);
        if(flag)
        {
            SQLConstant.setNowPath(dbName);
            System.out.println("当前数据库: "+dbName);
        }
        else
        {
            System.out.println("ERROR: 数据库不存在");
        }
        Input.get();
    }
    /* */
    /*
    public static void useSqlWhenCreateTabel(String dbName)
    {
        String path = SQLConstant.getPath();
        List<String> dbList = Tools.getAllDatabase(path);
        boolean flag = dbList.contains(dbName);
        if(flag)
        {
            SQLConstant.setNowPath(dbName);
            System.out.println("Database: "+dbName);
        }
        else
        {
            System.out.println("ERROR: 数据库不存在");
        }
    }
    */
}