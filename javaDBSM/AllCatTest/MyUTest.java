package AllCatTest;

import src.zcmu.cat.SQLUser;

public class MyUTest {

    public static void main(String[] args) {
        
        String uName = "John";
        String uPasswords = "666666";
    
        //SQLUser.createUser(uName, uPasswords);
        SQLUser.userIdentify(uName, uPasswords);
    }
}
