package AllCatTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test{
    public static void main(String[] args) {
        //读文件
        String s;
        InputStream input = null;
        try{
            input = new FileInputStream("C:\\Users\\86150\\Desktop\\javaDBSM\\AllCatTest\\mytxt.txt");
            int n;
            StringBuilder sb = new StringBuilder();
            while((n=input.read())!=-1)
            {
                sb.append((char)n);
            }
            s = sb.toString();
            System.out.println("读取到的字符串是: "+s);
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
    }
}