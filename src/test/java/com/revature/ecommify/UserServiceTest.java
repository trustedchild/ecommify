package com.revature.ecommify;

import com.revature.ecommify.daos.UserDAO;
import com.revature.ecommify.models.User;
import com.revature.ecommify.services.UserService;
import com.revature.ecommify.utils.custom_exceptions.InvalidInputException;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
    }


    @Test
    public void test_isValidEmailAddress_givenCorrectEmail() {
        // Arrange
        String validEmail = "tguwor@revature.net";

        // Act
        boolean flag = sut.isValidEmailAddress(validEmail);

        // Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidEmailAddress_givenInCorrectEmailAddress() {
        // Arrange
        String invalidEmailAddress = "wsmith@";

        // Act
        sut.isValidEmailAddress(invalidEmailAddress);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidValidEmailAddress_givenEmptyEmailAddress() {
        // Arrange
        String invalidEmailAddress = "";

        // Act
        sut.isValidEmailAddress(invalidEmailAddress);
    }

    @Test
    public void test_isValidName_givenAlphaCharacters() {
        // Arrange
        String validFirstName = "james";

        // Act
        sut.isValidUserInput(validFirstName);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isNotValidName_CombinedWithNumbers() {
        // Arrange
        String invalidFirstName = "james20929";

        // Act
        sut.isValidUserInput(invalidFirstName);
    }


    @Test(expected = InvalidUserException.class)
    public void test_isNotValidZipCode_containsAlphaCharacterS() {
        // Arrange
        String invalidZipCode = "1234kh";

        // Act
        sut.isValidZipCode(invalidZipCode);
    }

    @Test
    public void test_isValidZipCode_givenCorrectZipcode() {
        // Arrange
        String validZipCode = "12345";

        // Act
        sut.isValidZipCode(validZipCode);
    }

}