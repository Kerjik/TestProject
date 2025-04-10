package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CinescopePage {

    //Кнопка "Подробнее" у фильма %s
    String chooseFilmBtn = "//*[contains (text(), '%s')]//following::button";

    //Цена билета на фильм
    private final SelenideElement priceOfFilm = $x("//*[contains(text(), 'Купить билет')]/following::*[1]");

    //Кнопка "Профиль"
    private final SelenideElement profileBtn = $x("//button[contains(text(), 'Профиль')]");

    //Кнопка "Админ панель"
    private final SelenideElement adminPanelBtn = $x("//*[contains(text(), 'Админ панель')]");

    //Кнопка "Пользователи в админ панели
    private final SelenideElement usersFromAdminPanel = $x("//*[contains(text(), 'Пользователи')]");

    //Кнопка "Создать пользователя"
    private final SelenideElement createNewUserBtn = $x("//*[contains(text(), 'Создать пользователя')]");

    //Поле ФИО в окне создания нового пользователя
    private final SelenideElement fullNameField = $(By.id("fullName"));

    //Поле email в окне создания нового пользователя
    private final SelenideElement emailField = $(By.id("email"));

    //Поле пароль в окне создания нового пользователя
    private final SelenideElement passwordFiled = $(By.id("password"));

    //Дропдаун роль пользователя
    private final SelenideElement roleOfNewUserField = $x("//*[contains(text(), 'Роль')]/following::button[1]");

    //Выбрать роль пользователя
    String chooseRoleOfNewUser = "//div[@role = 'option'][%d]";

    //email Нового пользователя
    private final SelenideElement emailOfNewUser = $x("(//div[@class = 'text-center'])[2]");

    //ФИО нового пользователя
    private final SelenideElement fullNameOfNewUser = $x("//div[@class = 'text-center'])[3]");

    //Роль нового пользователя
    private final SelenideElement roleOfNewUser = $x("//div[@class = 'text-center'])[4]");

    //Кнопка "Создать" в окне создания нового пользователя
    private final SelenideElement createBtn = $x("//button[text() = 'Создать']");

    public CinescopePage chooseFilm(String filmsName){
        String setFilm = String.format(chooseFilmBtn, filmsName);
        $x(setFilm).click();
        return this;
    }
    public int getIntPriceOfFilm(){
        return Integer.parseInt(priceOfFilm.getText().replaceAll("[^0-9]", ""));
    }
    public CinescopePage goToProfile(){
        profileBtn.click();
        return this;
    }
    public CinescopePage goToAdminPanel(){
        adminPanelBtn.click();
        return this;
    }
    public CinescopePage goToUsersFromAdminPanel(){
        usersFromAdminPanel.click();
        return this;
    }
    public CinescopePage createNewUser(){
        createNewUserBtn.click();
        return this;
    }
    public CinescopePage setFullNameToNewUser(String fullName){
        fullNameField.setValue(fullName);
        return this;
    }
    public CinescopePage setEmailToNewUser(String email){
        emailField.press("CTRL+A").press("BACKSPACE").setValue(email);
        return this;
    }
    public CinescopePage setPasswordToNewUser(String password){
        passwordFiled.press("CTRL+A").press("BACKSPACE").setValue(password);
        return this;
    }
    public int roleInt(String role){
        return switch (role) {
            case "USER" -> 1;
            case "ADMIN" -> 2;
            case "SUPER-ADMIN" -> 3;
            default -> 0;
        };
    }
    public CinescopePage setRoleForNewUser(int role){
        String setRole = String.format(chooseRoleOfNewUser, role);
        roleOfNewUserField.click();
        $x(setRole).click();
        return this;
    }
    public CinescopePage createSubmit(){
        createBtn.click();
        return this;
    }
    public CinescopePage assertEmailOfNewUser(String email){
        emailOfNewUser.should(Condition.text(email));
        return this;
    }
    public CinescopePage assertFullNameOfNewUser(String fullName){
        fullNameOfNewUser.should(Condition.text(fullName));
        return this;
    }
    public CinescopePage assertRoleOfNewUser(String role){
        roleOfNewUser.should(Condition.text(role));
        return this;
    }
}
