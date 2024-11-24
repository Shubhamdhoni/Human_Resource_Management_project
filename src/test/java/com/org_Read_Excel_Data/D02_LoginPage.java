package com.org_Read_Excel_Data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class D02_LoginPage {
    WebDriver driver;

    By usernameField = By.id("txtUsername");
    By passwordField = By.id("txtPassword");
    By loginButton = By.id("btnLogin");
    By logoutDropdown = By.id("welcome");
    By logoutLink = By.linkText("Logout");

    public D02_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void logout() {
        driver.findElement(logoutDropdown).click();
        driver.findElement(logoutLink).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElements(logoutDropdown).size() > 0;
    }
}
