
package java4;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.*;

public class user {

    private String login;
    private String pass;

    public user(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }


    
    boolean checkUser(String log, String pass)
    {
        try{
            boolean findUser = false;
            
           
            Scanner in = new Scanner(new File("file.txt"));
            while(in.hasNextLine()) {
                String[] strLine = in.nextLine().split(":");
                
//                for (int i = 0; i < strLine.length; i++){
//                    System.out.println(strLine[0] + );
//
//                }
//                System.out.println(strLine[1] + strLine[0].equals(log) + " " + strLine[1] + " " + strLine[1].equals(pass));
                if (strLine[0].equals(log) && strLine[1].equals(pass)) {
                    return true;
                }
            }
            return findUser;
        }catch (IOException e){
            System.out.println("Ошибка");
            return false;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
