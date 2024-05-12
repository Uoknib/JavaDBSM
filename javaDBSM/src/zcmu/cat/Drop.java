package src.zcmu.cat;
//12
import java.util.List;
//import java.util.Scanner;
import java.io.File;

public class Drop {
    
    //private static String sep;
    private static String dbName;
    private static String tableName;

    public static void dropSql(String sql)
    {
        //sep = SQLConstant.getSeparate();

        //  drop right judge
        /////
        String user = SQLUser.getUser();
        if(SQLUser.dWhoIs(user))
        {

        if(sql.contains("drop user "))//drop user uName;
        {
            String admin = SQLUser.getAdministrator();
            if(user.equals(admin))
            {
                String uName;
                uName = sql.substring(10, sql.length()-1);
                //
                /////
                //log
                if(dropUser(uName)) 
                {
                    Log.log(sql);
                    System.out.println("OK: 用户"+uName+"删除成功");
                }
                
                
                //
                Input.get(); 
            }
            else
            {
                System.out.println("ERROR: 用户权限不足");

                Input.get();
            }
           
        }
        //###
        else
        {

        
        if(sql.contains(" database "))
        {
            dbName = sql.substring(14, sql.length()-1);
            //log all
            ////////
            //1
            Log.log(sql);
            //
            dropDatabase();
        }
        else if(sql.contains(" table "))
        {
            tableName = sql.substring(11, sql.length()-1);
            /* */
            //2
            //
            Log.log(sql);
            //
            dropTable();
        }
        else
        {
            System.out.println("ERROR: 语句有错误");
            //
            //###
            Input.get();
            //
        }
    
        }
        }
        else
        {
            System.out.println("ERROR: 权限不足");     
            Input.get(); 
        }
    }
    //////
    //###
    private static boolean dropUser(String uName)
    {
        String path = SQLUser.getUserPath();
        String uPath = path + "\\" + uName + ".txt";
        
        File file = new File(uPath);
        if(!file.exists())
        {
            System.out.println("ERROR: 用户"+uName+"不存在");
            return false;
        }
        else
        {
            return file.delete();
        }
    }
    //###
    //////
    private static void dropDatabase()
    {
        String path = SQLConstant.getNowPath();
        List<String> dbList = Tools.getAllDatabase(path);
        
        boolean dbexist =  dbList.contains(dbName);
        if(dbexist)
        {
            
            boolean confirmdelete = confirm();
            if(confirmdelete)
            {
                /* */
                String nowPath = path + "\\" + dbName;
                deleteFile(new File(nowPath));
                System.out.println("OK: 数据库删除成功");
            }
            else
            {
                System.out.println("OOPS: 数据库删除失败");
            }
        }
        else
        {
            System.out.println("ERROR: 该数据库不存在");
        }

        Input.get();
    }
    ////* */
    public static boolean deleteFile(File dirFile)
    {
        if(!dirFile.exists())
        {
            return false;
        }
        if(dirFile.isFile())
        {
            return dirFile.delete();
        }
        else
        {
            for(File file:dirFile.listFiles())
            {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }
    ////* */

    private static boolean confirm()
    {
        System.out.println("确认删除: \"Yes\" or \"No\"");
        System.out.print("请输入: ");
        //###
        //Scanner scanner = new Scanner(System.in);
        //
        String input = Input.scanner.nextLine().trim().toLowerCase();
        //###
        boolean flag;
        //
        if("yes".equals(input)){
            //return true;
            flag = true;
        }
        else if("no".equals(input))
        {
            //return false;
            flag = false;
        }
        else
        {
            System.out.println("ERROR: 输入的单词无法识别");
            //return false;
            flag = false;
        }
        //scanner.close();//Warnning existException cant close?
        return flag;
    }

    private static void dropTable()
    {
        String path = SQLConstant.getNowPath();
        List<String> tableList = Tools.getAllDatabase(path);

        boolean tbexist = tableList.contains(tableName);
        if(tbexist)
        {
            boolean confirmdelete = confirm();
            if(confirmdelete)
            {
                String nowPath = path + "\\" + tableName + ".txt";
                File file = new File(nowPath);
                file.delete();
                System.out.println("OK: 表删除成功");
            }
            else
            {
                System.out.println("OOPS: 表删除失败");
            }
        }
        else
        {
            System.out.println("ERROR: 该表不存在");
        }

        Input.get();
    }
}
