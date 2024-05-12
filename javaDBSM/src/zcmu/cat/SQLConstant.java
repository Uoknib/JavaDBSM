package src.zcmu.cat;
//5//->1
//finished
public class SQLConstant {
    private static final String path = "C:\\Users\\86150\\Desktop\\diskCAT";
    private static String nowPath = path;
    private static final String separate = "~";
    
    public static String getPath()
    {
        return path;
    }
    public static String getNowPath()
    {
        return nowPath;
    }
    
    //exist wrong
    //just can use once
    // public static void setNowPath(String name)
    // {
    //     nowPath = nowPath + "\\" + name;
    // }

    //###
    public static void setNowPath(String name)
    {
        nowPath = path + "\\" + name;
    }

    public static String getSeparate()
    {
        return separate;
    }
}