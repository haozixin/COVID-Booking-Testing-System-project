package loginSubsystem;


import webServiceAPI.WebServices;
import enums.ResponseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem {

    private final WebServices serviceForUser;
    private List<String> dashBoard;
    private static final String systemName = "Login Subsystem";


    /**
     * Constructor
     */
    public LoginSubsystem(WebServices iWebServices) {
        serviceForUser = iWebServices;
        setDashBoard(64, 5);
    }

    /**
     * Do login logic
     */
    public void login() throws IOException, InterruptedException {
        display();
        int code = authentication();


        //TODO:需要优化
        if (code == ResponseStatus.CODE_200.getCode()) {
            System.out.println("successful login");
        } else {
            //TODO:不同状态码应该有不同的反馈，但不用if else写，不好扩展，可以写一个类装状态码，功能为code与“含义”的转换
            System.out.println("failed");
        }
    }

    /**
     * Generate a DashBoard/Banner for the system (can be reused by all kinds of system)
     *
     * @param width  the width you want
     * @param height the hdight you want
     * @return return ArrayList<String> contains the dashboard that needs to display
     */
    private void setDashBoard(int width, int height) {
        //TODO: 后期将此逻辑挪出去，再通过接口调用（各个子系统通用）
        String titleText = "Welcome to " + systemName;
        ArrayList<String> dashBoard = new ArrayList<>();
        String a = "=";
        String side = "===";
        for (int x = 0; x < height; x++) {
            String line = a;
            if (x == 0 || x == height - 1) {
                for (int y = (2 * (a.length())); y < width; y++) {
                    line = line.concat(a);
                }
            } else if (x != height / 2) {
                int leftPadding = width - (2 * side.length()) - 1;
                line = String.format(side + "%" + leftPadding + "s", "");
                line = line.concat(side);
            }

            if (x == height / 2) {
                int leftPadding = (width - side.length()) * 3 / 5;
                String title = String.format(side + "%" + leftPadding + "s", titleText);

                int rightPadding = (width - side.length()) - 1;
                title = String.format("%1$-" + rightPadding + "s" + side, title);
                line = title;
            }

            dashBoard.add(line);
        }
        this.dashBoard = dashBoard;

    }

    private int authentication() throws IOException, InterruptedException {
        String userName, password;

        System.out.print("Input your userName: ");
        Scanner s = new Scanner(System.in);
        userName = s.next();

        System.out.print("Input your password: ");
        password = s.next();

        // get token
        String token = serviceForUser.getToken(userName, password);

        // verify token and return status code
        return serviceForUser.verifyToken(token);
    }

//    /**
//     * you can add your own users account as well
//     */
//    private void addUser() {
//    }


    public void display() {
        //TODO: output better GUI for login system here

        for (String i : dashBoard) {
            System.out.println(i);
        }
        System.out.println("----------You are doing Login system operation---------");
    }

}
