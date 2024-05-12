package src.zcmu.cat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//11
//All Uncheck
public class Select {

    public static void selectSql(String sql){

        String sep = SQLConstant.getSeparate();
        String path = SQLConstant.getNowPath();
        String tableName = sql.substring(14, sql.length()-1);
        String nowPath = path + "\\" + tableName + ".txt";

        List<String> list = getTableDescribe(nowPath);
        String restrict1 = sql.substring(7, 8);
       
        boolean b = tableName.contains(" ");
        if(!b && restrict1.equals("*")) {

            List<String> columnName = Insert.getColumnName(nowPath, 1);

            List<List<String>> lists = new ArrayList<>();
            //want//CatOptimize -> foreach | for
            int i = 0;
            for (String s : list) {
                //Helpless
                s = s + s;
                //###
                List<String> list1 = new ArrayList<>();
                String[] strings = list.get(i++).split(sep);
                for (String s1 : strings) {
                    list1.add(s1);
                }
                lists.add(list1);
            }
            ////////
            // for(int i=0;i<list.size();i++)
            // {
            //     List<String> list1 = new ArrayList<>();
            //     String[] strings = list.get(i++).split(sep);
            //     for (String s1 : strings) {
            //         list1.add(s1);
            //     }
            //     lists.add(list1);
            // }
            ////////
            System.out.println(TableGenerator.generateTable(columnName, lists));

            System.out.println("OK: 操作成功");

        }
        else{
            System.out.println("ERROR: 目前的select命令不支持这种方式的查询");
        }


        Input.get();
    }
    private static List<String> getTableDescribe(String path){

        List<String> list = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = "";
            int index = 1;
            while((s = bufferedReader.readLine()) != null){
                index++;
                if(index > 4){
                    list.add(s);
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }
}