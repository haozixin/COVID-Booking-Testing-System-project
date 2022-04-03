package LoginSubsystem;


import WebServiceAPI.IWebServices;

import java.io.IOException;
import java.util.Scanner;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem {

    private final IWebServices serviceForUser;


    /**
     * Constructor
     */
    public LoginSubsystem(IWebServices iWebServices) {
        serviceForUser = iWebServices;
    }

    /**
     * Do login logic
     */
    public void login() throws IOException, InterruptedException {
        display();
        int code = authentication();


        //TODO:需要优化
        if (code == 200){
            System.out.println("successful login");
        }else{
            //TODO:不同状态码应该有不同的反馈，但不用if else写，不好扩展，可以写一个类装状态码，功能为code与“含义”的转换
            System.out.println("failed");
        }
    }


    private int authentication() throws IOException, InterruptedException {
        String userName, password;

        System.out.print("Input your userName: ");
        Scanner s = new Scanner(System.in);
        userName = s.next();

        System.out.print("Input your password: ");
        password = s.next();

        // get token
        String token = serviceForUser.getToken(userName,password);

        // verify token and return status code
        return serviceForUser.verifyToken(token);
    }

//    /**
//     * you can add your own users account as well
//     */
//    private void addUser() {
//    }


    private void display() {
        //TODO: output better GUI for login system here
        System.out.println("----------You are doing Login system operation---------");
    }

}
