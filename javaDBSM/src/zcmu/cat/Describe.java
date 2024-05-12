package src.zcmu.cat;
//9
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//finished
public class Describe {
    /*uncheck */
    public static void describeSql(String sql)
    {
        String sep = SQLConstant.getSeparate();

        String path = SQLConstant.getNowPath();
        String tableName = sql.substring(9,sql.length()-1);

        String nowPath = path + "\\" + tableName + ".txt";
        List<String> list = getTableDescribe(nowPath);

        List<List<String>> lists = new ArrayList<List<String>>();
        String []s1 = list.get(0).split(sep);
        String []s2 = list.get(1).split(sep);
        String []s3 = list.get(2).split(sep);
        for(int i=0;i<s1.length;i++)
        {
            List<String> list1 = new ArrayList<String>();
            list1.add(s1[i]);
            list1.add(s2[i]);
            list1.add(s3[i]);
            lists.add(list1);
        }
        List<String> list4 = new ArrayList<>();
        list4.add("Filed");
        list4.add("Type");
        list4.add("Extra");

        System.out.println(TableGenerator.generateTable(list4, lists));

        Input.get();
    }
    //
    private static List<String> getTableDescribe(String path)
    {
        List<String> list = new ArrayList<String>();

        try{
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s = "";
            int index = 1;
            while(((s=bufferedReader.readLine())!=null)&&(index<4))
            {
                index++;
                list.add(s);
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }
}