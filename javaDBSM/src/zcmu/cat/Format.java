package src.zcmu.cat;
//1 
//finished
public class Format {
    public static String sqlFormat(String input)
    {
        String sql = "";
        sql = input.trim();//delete space
        String string = sql.toLowerCase();
        String str = string.replaceAll("\\s{2,}"," ");
        String s = str.replaceFirst("( ;)$", ";");
        return s;
    }
}