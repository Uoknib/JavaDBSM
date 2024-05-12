package src.zcmu.cat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Log {

    private final static String path = SQLUser.getUserPath();
    private static String user = SQLUser.getUser(); 

    // private final static String path = "C:\\Users\\86150\\Desktop\\diskCAT";
    // private static String user = "sck"; 

    public static void log(String sql)
    {
        String sep = SQLConstant.getSeparate();

        String logPath = getLogPath();
        ////////
        //System.out.println(logPath);
        ////////
        File file = new File(logPath);

        Date logFileDate = new Date();
        String logDate = logFileDate.toString();

        String str = "";
        str = str + sql + sep + logDate;

        if(!file.exists())
        {
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(new File(logPath),true);
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

            //System.out.println("日志文件产生成功");

        }
        else
        {
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(new File(logPath),true);
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

            //System.out.println("日志文件插入成功");
        }
    }
    //
    private static String getLogPath()
    {
        return (path+"\\"+user+"logfile.txt");
    }
}
