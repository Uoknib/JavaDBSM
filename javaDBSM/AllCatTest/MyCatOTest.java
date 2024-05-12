package AllCatTest;
//
import src.zcmu.cat.CatOptimize;
import src.zcmu.cat.Format;
import java.util.Scanner;
public class MyCatOTest {
    public static void main(String[] args) {
        // //String sql = "create table doctor\n();";
        // Scanner input = new Scanner(System.in);
        // String sql = "";
        // while(sql.endsWith(";"))
        // {
        //     sql += input.nextLine();
        // }
        // int index = sql.indexOf("(");
        // CatOptimize.tableNameFormat(sql, index);

        // input.close();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do{
            System.out.print(">>");
            input += " "+scanner.nextLine();
            input = input.replaceFirst("(\\s+)$", "");
        }while(!input.endsWith(";"));
        String sql = Format.sqlFormat(input);

        int index = sql.indexOf("(");
        CatOptimize.tableNameFormat(sql, index);

        scanner.close();
    }
}
