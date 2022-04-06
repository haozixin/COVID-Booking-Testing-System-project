package loginSubsystem;

import webServiceAPI.Services;

public class LoginSubsystemTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void login() {

    }

    @org.junit.Test
    public void display() {
        LoginSubsystem loginSubsystem = new LoginSubsystem(new Services());
        loginSubsystem.display();
    }
}