package src.zcmu.cat;
//CatTools
public class CatOptimize {
    public static void useSqlWhenCreateTabel(String dbName)
    {
        //String path = SQLConstant.getPath();
        //List<String> dbList = Tools.getAllDatabase(path);
        //boolean flag = dbList.contains(dbName);
        SQLConstant.setNowPath(dbName);
        System.out.println("当前数据库: "+dbName);
    }
    public static boolean tableNameFormat(String sql,int index)
    {
        boolean flag;
        String tableNameLast = sql.substring(index-1, index);
        //for test
        //System.out.println("tableNameLast is"+tableNameLast+"|end");
        
        if(tableNameLast.equals(" ")||tableNameLast.equals("\n"))
        {   
            flag = false;
            //return false;
        }
        else//tableName without space and "\n"
        {
            flag = true;
            //return true; 
        }

        //for test
        //System.out.println(flag);

        return flag;
    }
}