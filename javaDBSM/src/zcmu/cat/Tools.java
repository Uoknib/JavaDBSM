package src.zcmu.cat;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

//other
//finished
public class Tools {
    public static boolean bracketMatch(String sql)
    {
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<sql.length();i++)
        {
            if((sql.charAt(i)=='('))
            {
                stack.push("(");
            }
            else if(sql.charAt(i)==')')
            {
                if(stack.empty())
                    return false;
                else
                {
                    stack.pop();
                }
            }
        }
        return (stack.empty());
    }
    public static List<String> getAllDatabase(String path)
    {
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        File []fileList = file.listFiles();
        for(int i=0;i<fileList.length;i++)
        {
            if(fileList[i].isDirectory())
            {
                list.add(fileList[i].getName());
            }
        }
        return list;
    }
    public static List<String> getAllTables(String nowPath)
    {
        List<String> list = new ArrayList<String>();
        File file = new File(nowPath);
        File []fileList = file.listFiles();
        if(fileList!=null)
        {
            for(int i=0;i<fileList.length;i++)
            {
                if(fileList[i].isFile())
                {
                    String name = fileList[i].getName();
                    int index = name.lastIndexOf(".");
                    String tableName = name.substring(0, index);
                    list.add(tableName);
                }
            }
        }
        else
        {
            //
        }
        return list;
    }
}
