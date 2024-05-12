package src.zcmu.cat;
//###4
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//finished
public class Create {

    private static final String path = SQLConstant.getPath();
    private static String tableName = "";

    public static void createSql(String sql)
    {
        String user = SQLUser.getUser();
        if(SQLUser.cWhoIs(user))////权限判断
        {
        boolean createUse = sql.contains(" user ");
        if(createUse)//create user Tom 123123;
        {
            //System.out.println(sql);
            String [] str = sql.split(" ");
            // for(String s:str)
            //     System.out.println(s);
            String userName = str[2];


            int index = str[3].indexOf(";");
            String uPasswords = str[3].substring(0, index);
            ///System.out.println(uPasswords); 

            SQLUser.createUser(userName, uPasswords);
            //$$$
            //success//1
            Log.log(sql);

            Input.get();
        }
        else
        {
        boolean cdb = sql.contains(" database ");
        if(cdb)
        {
            String dbName = sql.substring(16, sql.length()-1);
            ////////
            //2
            Log.log(sql);
            ////////
            createDir(dbName);
            //
            //success
            //Log.log(sql);
        }
        else
        {
            boolean ctb = sql.contains(" table ");
            if(ctb)
            {
                if(Tools.bracketMatch(sql)) 
                {
                    ////////
                    //3
                    Log.log(sql);
                    ////////
                    createTable(sql);
                    //
                    //success
                    //Log.log(sql);
                }
                else
                {
                    System.out.println("ERROR: 语句有错误");
                    Input.get();
                }
            }
            //###
            else
            {
                System.out.println("ERROR: 语句有错误");
                Input.get();
            }
            //###
        }
        }
        }
        else
        {
            System.out.println("ERROR: 权限不足");
            Input.get();
        }
    }
    private static void createDir(String dbName)
    {
        File file = new File(path + "\\" + dbName);
        if(!file.exists())
        {
            file.mkdir();
            System.out.println("OK: 数据库创建成功");
            /* */
            //Use.useSqlWhenCreateTabel(dbName);
            CatOptimize.useSqlWhenCreateTabel(dbName);
        }
        else
        {
            System.out.println("ERROR: 该数据库已经存在");
        }
        Input.get();
    }
    private static void createTable(String sql)
    {
        //###
        //optimize tablename "one ()" -> "one "
        //want "one\n()"" -> "one"
        String tablePath = SQLConstant.getNowPath();
        int index = sql.indexOf("(");
        if(index != -1)
        {
            if(CatOptimize.tableNameFormat(sql, index))
                tableName = sql.substring(13, index) + ".txt";//original
            else
                tableName = sql.substring(13,index-1) + ".txt";
            //###    
            tableName.trim();
            File table = new File(tablePath, tableName);
            if (!table.exists())
            {
                try {
                    table.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String sep = SQLConstant.getSeparate();
                String str = sql.replaceAll(sep + "", sep + sep);
                Pattern pattern = Pattern.compile("\\(.*\\)");
                Matcher matcher = pattern.matcher(str);
                String s = "";
                if (matcher.find()) 
                {
                    s = matcher.group(0);
                }
                String s1 = s.substring(1, s.length() - 1);
                String[] strings = s1.split(",");
                List<String> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                List<String> list3 = new ArrayList<>();
                for(String s2: strings)
                {
                    String s3 = s2.trim();
                    String[] strings1 = s3.split(" ");
                    list1.add(strings1[0]);
                    list2.add(strings1[1]);
                    for(int i = 2; i < strings1.length; i++)
                    {
                        if(strings1[i].equals("not") && strings1[i+1].equals("null")){
                            list3.add("not null");
                            i++;
                        }
                        else{
                            list3.add(strings1[i]);
                        }
                    }
                }
                writeFile(list1);
                writeFile(list2);
                writeFile(list3);
                System.out.println("OK: 表创建成功");
            }
            else{
                System.out.println("ERROR: 该表已经存在");
            }
        }
        else{
            System.out.println("ERROR: 语句有错误");
        }
        Input.get();
    }
    private static void writeFile(List<String> list){
        String str = "";
        String sep = SQLConstant.getSeparate();
        String path = SQLConstant.getNowPath();
        String nowPath = path + "\\" + tableName;
        for(String s1: list)
        {
            str += s1 + sep;
        }
        try{
            FileOutputStream fos = new FileOutputStream(
                    new File(nowPath), true);
            str += "\r\n";
            fos.write(str.getBytes());
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}