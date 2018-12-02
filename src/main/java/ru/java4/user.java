
package java4;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.*;

public class user {

    private String firstName;
    private String lastName;
    private String login;
    private String pass;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public user(String firstName, String lastName, String login, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.pass = pass;
    }

    String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    boolean checkUser(String log, String pass)
    {
        /*
        найти пользователя через текстовый файл
         */
        try{
            boolean findUser = false;
            Scanner in = new Scanner(new File("file.txt"));
            while(in.hasNextLine()) {
                String[] strLine = in.nextLine().split(":");
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



}
