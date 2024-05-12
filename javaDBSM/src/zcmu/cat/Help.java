package src.zcmu.cat;
//6
//finished
public class Help {
    public static void help(){
        // System.out.println("1.create database 数据库名: 用于创建数据库");
        // System.out.println("2.show databases: 列出目前已经创建的数据库");
        // System.out.println("3.use 数据库名: 选择使用的数据库");
        // System.out.println("4.drop database 数据库名");
        System.out.println("1.quit");
        System.out.println(">>quit: 用于退出系统");
        System.out.println("2.create");//
        System.out.println(">>create user 用户名 密码: 用于创建用户");
        //System.out.println("========================================");
        System.out.println(">>create database 数据库名: 用于创建数据库");
        System.out.println(">>create table 表名(...): 用于创建表");
        System.out.println("3.show");
        System.out.println(">>show databases: 用于列出所有数据库");
        System.out.println(">>show tables: 用于列出所有表");
        System.out.println("4.use");
        System.out.println(">>use 数据库名: 用于选择使用的数据库");
        System.out.println("5.drop");//
        System.out.println(">>drop user 用户名: 用于删除用户,仅管理员可操作");
        System.out.println(">>drop database 数据库名: 用于删除数据库");
        System.out.println(">>drop table 表名: 用于删除表");
        System.out.println("6.grant");
        System.out.println("ps: 只有管理员有权限"); /*只有管理员可以授权 */
        System.out.println(">>grant create to 用户名: 用于授予用户create权限");
        System.out.println(">>grant drop to 用户名: 用于授予用户drop权限");
        System.out.println(">>grant insert to 用户名: 用于授予用户insert权限");
        System.out.println("7.conn");
        System.out.println(">>conn 用户名/密码: 用于登入用户");
        System.out.println("other common SQL语句: select,describe......");
        System.out.println("==========================================");
        //
        //
        //duser      -> hava drop 
        //iuser      -> hava insert
        //tom        -> no right
        //zcmucat    -> have creat & drop
        //zcmucatcat -> have all
        //
        //

        Input.get();
    }
}
