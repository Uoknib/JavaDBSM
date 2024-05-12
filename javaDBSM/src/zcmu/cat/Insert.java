package src.zcmu.cat;
//10
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Insert {
    private static String tableName;

    public static void insertSql(String sql)
    {
        //judge right
        String uName = SQLUser.getUser();
        if(SQLUser.iWhoIs(uName))
        {
        int index = sql.indexOf("(");
        String name = sql.substring(12,index);
        //
        //
        tableName = name.trim();

        String path = SQLConstant.getNowPath();
        List<String> list = Tools.getAllTables(path);
        boolean tbexist = list.contains(tableName);
        if(tbexist)
        {
            String nowPath = path + "\\" + tableName + ".txt";

            List<String> list1 = new ArrayList<String>();
            //uncheck
            //
            Pattern pattern = Pattern.compile("\\(.*?\\)");
            Matcher matcher = pattern.matcher(sql);
            while(matcher.find())
            {
                list1.add(matcher.group());
            }
            //###
            //think exist wrong below
            //
            if(list1.size()!=2)
            {
                System.out.println("ERROR: 语句有错误");
                Input.get();
            }
            else
            {
                List<String> columnName = getColumnName(nowPath,1);
                List<String> type = getColumnName(nowPath,2);

                String s1 = list1.get(0).substring(1,list1.get(0).length()-1).trim();
                String s2 = list1.get(1).substring(1,list1.get(1).length()-1).trim();
                
                String s22 = transMean(s2);

                String[] key = s1.split(",");
                String[] value = s22.split(",");

                for(int i=0;i<key.length;i++)
                {
                    key[i] = key[i].trim();
                }
                for(int i=0;i<value.length;i++)
                {
                    value[i] = value[i].trim();
                }

                String sep = SQLConstant.getSeparate();
                int len = columnName.size();
                String s = "";//the final String to save
                int flag1 = 0;
                for(int i=0;i<len;i++)
                {
                    String s3 = columnName.get(i);
                    int flag = 0;
                    for(int j=0;j<key.length;j++)
                    {
                        if(key[j].equals(s3))
                        {
                            String s4 = check(type.get(i),value[j]);
                            if(s4==null)
                            {
                                flag1 = 1;
                                break;
                            }
                            //? Specify the input ?
                            else if(s4=="zcmucat")
                            {
                                s += value[j] + sep;
                                flag = 1;
                            }
                            else
                            {
                                s += s4 + sep;
                                flag = 1;
                            }
                        }
                    }
                    if(flag==0)
                    {
                        s += "null" + sep;
                    }
                    if(flag1==1)
                    {
                        break;
                    }
                }
                if(flag1==0)
                {
                    writeFile(s);
                    //recording logfile
                    //1
                    Log.log(sql);
                    //
                    System.out.println("OK: 数据插入表"+tableName+"成功");
                }
                Input.get();
            }
        }
        else
        {
            System.out.println("ERROR: 该表不存在");
            Input.get();
        }
        }
        else
        {
            System.out.println("ERROR: 权限不足");
            Input.get();
        }
    }

    //part uncheck

    public static List<String> getColumnName(String path,int i)
    {
        List<String> list = new ArrayList<String>();
        //
        File file = null;
        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try{
            // File file = new File(path);
            // FileReader reader = new FileReader(file);
            //BufferedReader bufferedReader = new BufferedReader(reader);
            
            file = new File(path);
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);

            String s = "";
            int index = 0;

            while((s = bufferedReader.readLine())!=null)
            {
                index++;
                if(index==i)
                {
                    String sep = SQLConstant.getSeparate();
                    String[] strings = s.split(sep);
                    for(String s1:strings)
                    {
                        list.add(s1);
                    }
                    return list;
                }
            }
            //bufferedReader.close();//warning: bufferedReader isnot close
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(bufferedReader!=null)
                {
                    bufferedReader.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        return list;
    }

    private static String transMean(String str)
    {
        String sep = SQLConstant.getSeparate();
        String s = str.replaceAll(sep, sep + sep);

        return s;
    }
    //
    private static void writeFile(String s)
    {
        //didnot use sep
        //String sep = SQLConstant.getSeparate();

        String path = SQLConstant.getNowPath();
        String nowPath = path + "\\" + tableName + ".txt";

        try{

            FileOutputStream fos = new FileOutputStream(
                new File(nowPath),true
            );

            s += "\r\n";
            fos.write(s.getBytes());
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static String check(String type,String str)
    {
        boolean b = type.contains("char");
        if(b)
        {
            Pattern pattern = Pattern.compile("\".*\"");
            Matcher matcher = pattern.matcher(str);
            if(matcher.find())
            {
                String s = str.replaceAll("\"", "");
                return s;
            }
            else
            {
                System.out.println("ERROR: 输入值的格式错误");
                return null;
            }
        }
        else
        {
            return "zcmucat";//? Specify the input ?
        }
    }
}