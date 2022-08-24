package com.revature.ecommify;

import com.revature.ecommify.daos.ProductDAO;
import com.revature.ecommify.daos.UserDAO;
import com.revature.ecommify.services.ProductService;
import com.revature.ecommify.services.UserService;
import com.revature.ecommify.utils.custom_exceptions.InvalidInputException;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService sut;
    private final ProductDAO mockUserDao = mock(ProductDAO.class);

    @Before
    public void setup() {
        sut = new ProductService(mockUserDao);
    }


    @Test(expected = InvalidInputException.class)
    public void test_productNameInput_invalidInputGivenStringWithSpecialCharacter() {
        // Arrange
        ProductService spiedSut = Mockito.spy(sut);
        String incorrectProduct = "#$5^product";

        when(mockUserDao.isValidInput(incorrectProduct)).thenReturn(true);

        // Act
        sut.isValidInput(incorrectProduct);
    }

}
