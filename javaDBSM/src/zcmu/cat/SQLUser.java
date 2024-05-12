package src.zcmu.cat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SQLUser {
    private static final String path = SQLConstant.getPath();
    //private static final String path = "C:\\Users\\86150\\Desktop\\diskCAT";

    private static final String originalUserName = "zcmucatcat";
    private static String userName = originalUserName;
    //private static final String originalUserPasswords = "123123";
    //private static String userPasswords  = originalUserPasswords;


    //private static final boolean who = true;
    //private static final boolean who = false;


    //RIGHT
    //create right -> "~""
    //drop right -> "$"
    private static String isep = "@";
    //insert right
    public static String getISep()
    {
        return isep;
    }
    private static boolean judgeCreateUser(String uName)//能否创建当前用户,已存在false
    {
        String userPath = path + "\\" + encapsulatedUserName(uName);
        File file = null;
        file = new File(userPath);

        return (!file.exists());
    }
    private static String encapsulatedUserName(String uName)
    {
        return (uName + ".txt");
    }

    public static void createUser(String uName,String uPasswords)//创建用户
    {
        if(judgeCreateUser(uName))//用户存在判断
        {
        if(cWhoIs(userName))//用户权限判断
        {
           
        String str = "";
        String sep = SQLConstant.getSeparate();
        
        //String userPath = path + "\\" + uName + ".txt";

        String userPath = path + "\\" + encapsulatedUserName(uName);

        str = str + uName + sep + uPasswords;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(new File(userPath),true);
            //###
            ///**/str = str + sep + "false" + sep + "false";//create//drop//
            //// 
            //str = str + sep + "true" + sep + "true";
            //
            ////haha creat admin//////###
            //->
            //str = str + sep + "true" + "$" + "true" + isep + "true"; 
            ///////
            str += "\r\n";
            //str += "false~false";
            //###
            fos.write(str.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fos!=null)
                    fos.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        System.out.println("OK: 用户创建成功");
        }
        else
        {
            System.out.println("ERROR: 权限不足");
        }
        }
        else
        {
            System.out.println("ERROR: 用户已经存在");
        }
    }

    // public static boolean whoIs()//当前用户是谁...当前用户有无创建权限
    // {
    //     return who;
    // }

    public static boolean cWhoIs(String uName)//create right on uName
    {
        String sep = SQLConstant.getSeparate();
        String userPath = path + "\\" + encapsulatedUserName(uName);

        File file = new File(userPath);
        if(!file.exists())
        {
            //System.out.println("用户"+uName+"不存在");
            return false;
        }
        
        
        String s = "";
        InputStream input = null;
        try{
            input = new FileInputStream(userPath);
            int n;
            StringBuilder sb = new StringBuilder();
            while((n=input.read())!=-1)
            {
                sb.append((char)n);
            }
            s = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(input!=null)
                    input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //judge right
        //String []right = new String[2];
        int one = s.indexOf(sep);
        if(one!=-1)
        {
            int two = s.indexOf(sep, one+1);
            if(two!=-1)
            {
                //int three = s.indexOf(sep, two+1);
                //right[0] = s.substring(two+1, three);
                return true;   
            }
        }

        return false;
    }

    public static boolean userIdentify(String uName,String uPasswords)//用户认证
    {
        String sep = SQLConstant.getSeparate();
        String userPath = path + "\\" + encapsulatedUserName(uName);
        //user exist or no
        File file = new File(userPath);
        if(!file.exists())
        {
            System.out.println("ERROR: 用户不存在");
            return false;
        }
        //////
        String s = "";
        InputStream input = null;
        try{
            input = new FileInputStream(userPath);
            int n;
            StringBuilder sb = new StringBuilder();
            while((n=input.read())!=-1)
            {
                sb.append((char)n);
            }
            s = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(input!=null)
                    input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //identify
        String password = "";
        if(!s.equals(""))
        {
            //int index = s.indexOf("~");
            int index = s.indexOf(sep);
            if(index!=-1)
            { 
                //int twoindex = s.indexOf("~", index+1);
                int twoindex = s.indexOf(sep, index+1);//create
                int dindex = s.indexOf("$", index+1);
                //
                String isep = SQLUser.getISep();
                //
                int iindex = s.indexOf(isep, dindex+1);
                if(twoindex!=-1)
                    password = s.substring(index+1, twoindex);
                else if(dindex!=-1)
                    password = s.substring(index+1, dindex);
                else if(iindex!=-1)
                    password = s.substring(index+1, iindex);
                else
                    password = s.substring(index+1,s.length()-1-1);//~666666\r\n
                //////
                //System.out.println(password);
                //////
            }
        }
        // if(password.equals(uPasswords))
        //     return true;
        // else
        //     return false;
        boolean flag;
        if(password.equals(uPasswords))
        {
            System.out.println("OK: 登入成功");
            setUser(uName);
            flag = true;
        }
        
        else
        {
            System.out.println("ERROR: 密码错误");
            flag = false;
        }
        
        //
        //System.out.println(flag);
        //
        return flag;    
    }

    private static void setUser(String user)
    {
        userName = user;
        System.out.println("当前用户: "+userName);
    }
    public static String getUser()
    {
        return userName;
    }
    public static String getAdministrator()
    {
        return originalUserName;
    }
    public static String getUserPath()
    {
        return path;
    }

    ////drop right on uName
    public static boolean dWhoIs(String uName)
    {
        //String sep = SQLConstant.getSeparate();
        //String csep = SQLConstant.getSeparate();
        String dsep = "$";//实现drop权限的定位查找
        ////////
        ////
        //
        String userPath = path + "\\" + encapsulatedUserName(uName);

        File file = new File(userPath);
        if(!file.exists())
        {
            //System.out.println("用户"+uName+"不存在");
            return false;
        }
        ////read
        String s = "";
        InputStream input = null;
        try{
            input = new FileInputStream(userPath);
            int n;
            StringBuilder sb = new StringBuilder();
            while((n=input.read())!=-1)
            {
                sb.append((char)n);
            }
            s = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(input!=null)
                    input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //read into s

        //judge right
        // int one = s.indexOf(sep);
        // if(one!=-1)
        // {
        //     int two = s.indexOf(sep, one+1);
        //     if(two!=-1)
        //     {
        //         int three = s.indexOf(sep, two+1);
        //         //right[0] = s.substring(two+1, three);
        //         if(three!=-1)
        //             return true;
        //     }
        // }

        // return false;
        int dropRight = s.indexOf(dsep);
        if(dropRight!=-1)
        {
            return true;
        }
        else
            return false;
    }


    ////insert right on uName
    public static boolean iWhoIs(String uName)
    {
        String userPath = path + "\\" + encapsulatedUserName(uName);
        File file = new File(userPath);
        if(!file.exists())
        {
            return false;
        }
        ////read
        String s = "";
        InputStream input = null;
        try{
            input = new FileInputStream(userPath);
            int n;
            StringBuilder sb = new StringBuilder();
            while((n=input.read())!=-1)
            {
                sb.append((char)n);
            }
            s = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(input!=null)
                    input.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //read into s
        //judge right
        int dropRight = s.indexOf(isep);
        if(dropRight!=-1)
        {
            return true;
        }
        else
            return false;
    }
}
