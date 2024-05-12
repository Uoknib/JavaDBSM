package src.zcmu.cat;
//7
import java.util.List;
import java.util.ArrayList;
//finished
public class Show {
    public static void showSql(String sql)
    {
        boolean sdb = sql.endsWith(" databases;");
        if(sdb)
        {
            String path = SQLConstant.getPath();
            List<String> dbList = Tools.getAllDatabase(path);
            List<String> db = new ArrayList<String>();
            db.add("Database");
            List<List<String>> list = new ArrayList<List<String>>();
            for(int i=0;i<dbList.size();i++)
            {
                List<String> ls = new ArrayList<String>();
                ls.add(dbList.get(i));
                list.add(ls);
            }
            System.out.println(TableGenerator.generateTable(db,list));
            Input.get();
        }
        else
        {
            boolean stb = sql.endsWith(" tables;");
            if(stb)
            {
                String nowPath = SQLConstant.getNowPath();
                List<String> tableList = Tools.getAllTables(nowPath);
                List<String> list = new ArrayList<String>();

                int index = nowPath.lastIndexOf("\\");
                String dbName = nowPath.substring(index+1,nowPath.length());
                list.add(dbName);

                List<List<String>> tList = new ArrayList<List<String>>();
                for(String table:tableList)
                {
                    List<String> list1 = new ArrayList<String>();
                    list1.add(table);
                    tList.add(list1);
                }

                System.out.println(TableGenerator.generateTable(list, tList));
                Input.get();
            }
            else
            {
                System.out.println("ERROR: 语句有错误");
                Input.get();
            }
        }
    }
}
