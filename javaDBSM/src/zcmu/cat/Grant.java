package src.zcmu.cat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Grant {

    public static void grant(String sql)
    {
        
        boolean gcr = sql.contains(" create to ");
        if(gcr)//grant create to uName;
        {
            String uName = sql.substring(16,sql.length()-1);
                //grantCreate(uName);
            if(grantCreate(uName))
                Log.log(sql);
        }
        else
        {   
            boolean gdr = sql.contains(" drop to ");
            if(gdr)//grant drop to uName;
            {
                String uName = sql.substring(14, sql.length()-1);
                    //grantDrop(uName);
                if(grantDrop(uName))
                    Log.log(sql);
            }
            else
            {
                boolean gir = sql.contains(" insert to ");
                if(gir)//grant insert to uName;
                {
                    String uName = sql.substring(16, sql.length()-1);
                    if(grantInsert(uName))
                        Log.log(sql);
                }
                else
                {
                    System.out.println("ERROR: 语句有错误");
                }
                
            }
        }
        Input.get();
    }


    private static boolean grantInsert(String uName)
    {
        String administrator = SQLUser.getAdministrator();
        String nowUserName = SQLUser.getUser();
        if(nowUserName.equals(administrator))
        {
            String str = "";
            String sep = SQLUser.getISep();
            String path = SQLUser.getUserPath();
            String userPath = path + "\\" + uName + ".txt";

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
        //s
            s = s.substring(0,s.length()-1-1);//delete \r\n
        
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(new File(userPath),false);
                str = s + sep + "true";
                str += "\r\n";
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
            System.out.println("OK: insert 权限授予成功");
            return true;
        }
        else
        {
            System.out.println("ERROR: 权限不足");
            return false;
        }
    }

    ////////
    private static boolean grantCreate(String uName)//only admin can
    {
        String administrator = SQLUser.getAdministrator();
        String nowUserName = SQLUser.getUser();
        if(nowUserName.equals(administrator))
        {
            
            String str = "";
            String sep = SQLConstant.getSeparate();
            String path = SQLUser.getUserPath();
            String userPath = path + "\\" + uName + ".txt";

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
        //s
            s = s.substring(0,s.length()-1-1);//delete \r\n
        
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(new File(userPath),false);
                str = s + sep + "true";
                str += "\r\n";
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
            System.out.println("OK: create 权限授予成功");
            return true;
        }
        else
        {
            System.out.println("ERROR: 权限不足");
            return false;
        }
    }


    private static boolean grantDrop(String uName)
    {
        String administrator = SQLUser.getAdministrator();
        String nowUserName = SQLUser.getUser();
        if(nowUserName.equals(administrator))
        {
            
            String str = "";
            //get create-sep
            //String sep = SQLConstant.getSeparate();
            //define drop -sep
                String sep = "$";
            String path = SQLUser.getUserPath();
            String userPath = path + "\\" + uName + ".txt";

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
        //s
            s = s.substring(0,s.length()-1-1);//delete \r\n
        
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(new File(userPath),false);
                str = s + sep + "true";
                str += "\r\n";
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
            System.out.println("OK: drop 权限授予成功");
            return true;
        }
        else
        {
            System.out.println("ERROR: 权限不足");
            return false;
        }
    }


}
