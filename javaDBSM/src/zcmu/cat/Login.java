package src.zcmu.cat;

public class Login {
    public static void loginSql()
    {
        
        String name = "";
        String passwords = "";
        boolean flag;
        do
        {
            System.out.print("输入用户名: ");
            name = Input.scanner.next();
            System.out.print("输入密码: ");
            passwords = Input.scanner.next();
            flag = SQLUser.userIdentify(name,passwords);
            // if(!flag)
            //     System.out.println("ERROR: 用户名或密码错误");
        }while(!flag);
        // if(flag)    
        //     System.out.println("OK: 登入成功");
    }
}