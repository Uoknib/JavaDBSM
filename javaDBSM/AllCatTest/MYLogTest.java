package AllCatTest;

import src.zcmu.cat.Log;

public class MYLogTest {
    public static void main(String[] args) {
        
        String sql = "create database one;";
        Log.log(sql);

    }
}
