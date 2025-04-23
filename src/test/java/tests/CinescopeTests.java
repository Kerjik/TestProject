package tests;

import DataOfTests.FilmsAndPrice;
import DataOfTests.NewUsers;
import com.codeborne.selenide.Selenide;
import fakeUser.Address;
import fakeUser.Geolocation;
import fakeUser.Name;
import fakeUser.UserRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;


public class CinescopeTests extends BaseTest{

    private static Stream<Arguments> testFilmsAndPrice(){
        return Stream.of(
                Arguments.of(new FilmsAndPrice("Красотки", 100)),
                Arguments.of(new FilmsAndPrice("фцв", 100))

        );
    }

    private static Stream<Arguments> newUsers(){
        return Stream.of(
                Arguments.of(new NewUsers("qew qwe", "qwe@mail.ru", "123", "ADMIN"))

        );
    }

    @ParameterizedTest
    @MethodSource("testFilmsAndPrice")
    public void filmsPriceTest(FilmsAndPrice filmsAndPrice){
        CinescopePage cinescopePage = new CinescopePage();
        authorization();
        int actualPriceOfFilm = cinescopePage.chooseFilm(filmsAndPrice.getNameOfFilm()).getIntPriceOfFilm();
        Assertions.assertEquals(filmsAndPrice.getPriceOfFilm(), actualPriceOfFilm);
    }

    @ParameterizedTest
    @MethodSource("newUsers")
    public void createNewUser(NewUsers newUsers){
        CinescopePage cinescopePage = new CinescopePage();
        authorization();
        cinescopePage
                .goToProfile()
                .goToAdminPanel()
                .goToUsersFromAdminPanel()
                .createNewUser()
                .setFullNameToNewUser(newUsers.getFullName())
                .setEmailToNewUser(newUsers.getEmail())
                .setPasswordToNewUser(newUsers.getPassword())
                .setRoleForNewUser(cinescopePage.roleInt(newUsers.getRole()))
                .createSubmit()
                .assertEmailOfNewUser(newUsers.getEmail())
                .assertRoleOfNewUser(newUsers.getRole())
                .assertFullNameOfNewUser(newUsers.getFullName());
        sleep(5000);
    }

}