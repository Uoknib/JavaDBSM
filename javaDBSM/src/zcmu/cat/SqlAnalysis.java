package src.zcmu.cat;
//2
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SqlAnalysis {
    private static final String create = "create";//~
    private static final String use = "use";
    private static final String help = "help";//
    private static final String show = "show";//
    private static final String quit = "quit";//
    private static final String describe = "describe";
    private static final String insert = "insert";/////////
    private static final String select = "select";
    private static final String drop = "drop";//$

    private static final String grant = "grant";//grant create to usename;
    private static final String conn = "conn";//conn zcmu/123456;

    public static void analysis(String sql){
        String start = "";
        String regex = "^[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        while(matcher.find())
        {
            start = matcher.group();
        }
        switch (start){
            case create:
                Create.createSql(sql);
                break;
             case help:
                 Help.help();
                 break;
             case show:
                 Show.showSql(sql);
                 break;
            case use:
                Use.useSql(sql);
                break;
             case quit:
                 Quit.quitSql();
                 break;
             case describe:
                 Describe.describeSql(sql);
                 break;
             case insert:
                 Insert.insertSql(sql);
                 break;
            case select:
                Select.selectSql(sql);
                break;
            case drop:
                Drop.dropSql(sql);
                break;
            case grant:
                 Grant.grant(sql);
                 break;
            case conn:
                 Conn.conn(sql);
                 break;
            default:
                System.out.println("输入的命令无法识别,可以输入help查看目前支持的sql语句");
                Input.get();
                break;
        }
    }
}