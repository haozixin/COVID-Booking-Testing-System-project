package LoginSubsystem;


import WebServiceAPI.IWebServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem {
    private ArrayList<User> userArrayList;
    private IWebServices serviceForUser;


    /**
     * Constructor
     */
    public LoginSubsystem(IWebServices iWebServices) {
        serviceForUser = iWebServices;
    }

    /**
     * Do login logic
     */
    public void login() {
        System.out.println("I am in login subsystem");
        getToken();
    }


    private void getToken(){
        String userName, password;

        System.out.print("Input your userName: ");
        Scanner s = new Scanner(System.in);
        userName = s.next();

        System.out.print("Input your password: ");
        password = s.next();

        serviceForUser.authentication(userName,password);
    }

    /**
     * you can add your own users account as well
     */
    private void addUser() {
    }

    /**
     * setter
     * Get all users through web services and stored as user instance
     */
    public void setUserArrayList(User user) {
        //TODO: 判断是否为空，已存在于列表中等特殊情况
        this.userArrayList.add(user);
    }

    private void display() {
        //TODO: output better GUI for login system here
        System.out.println("----------You are doing Login system operation---------");
    }

}
