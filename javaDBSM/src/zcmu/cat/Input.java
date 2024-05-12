package src.zcmu.cat;
//3
import java.util.Scanner;
//finished


// public class Input {
//     public static void get(){
//         Scanner scanner = new Scanner(System.in);
//         String input = "";
//         do{
//             System.out.print(">>");
//             input += " "+scanner.nextLine();
//             input = input.replaceFirst("(\\s+)$", "");
//         }while(!input.endsWith(";"));
//         String sql = Format.sqlFormat(input);
//         SqlAnalysis.analysis(sql);
        
//         scanner.close();
//     }
// }
public class Input {
    //###
    static Scanner scanner = new Scanner(System.in);
    //
    public static void get(){
        //Scanner scanner = new Scanner(System.in);
        String input = "";
        do{
            System.out.print(">>");
            input += " "+scanner.nextLine();
            input = input.replaceFirst("(\\s+)$", "");
        }while(!input.endsWith(";"));
        String sql = Format.sqlFormat(input);
        SqlAnalysis.analysis(sql);
        
        //scanner.close();
    }
}