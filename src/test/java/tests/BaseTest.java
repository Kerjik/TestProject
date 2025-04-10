package tests;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class BaseTest {

    public void authorization(){
        open("https://cinescope.t-qa.ru/");
        $x("//button[contains (text(), 'Войти')]").click();
        $(By.id("email")).sendKeys("test-admin@mail.com");
        $(By.id("password")).setValue("KcLMmxkJMjBD1").pressEnter();
    }
}