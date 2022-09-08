package com.portfolio.ecommify;

import com.portfolio.ecommify.daos.ProductDAO;
import com.portfolio.ecommify.services.ProductService;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService sut;
    private final ProductDAO mockProductDao = mock(ProductDAO.class);

    @Before
    public void setup() {
        sut = new ProductService(mockProductDao);
    }


    @Test(expected = InvalidInputException.class)
    public void test_productNameInput_invalidInputGivenStringWithSpecialCharacter() {
        // Arrange
        ProductService spiedSut = Mockito.spy(sut);
        String incorrectProduct = "#$5^product";

        when(mockProductDao.isValidInput(incorrectProduct)).thenReturn(true);

        // Act
        sut.isValidInput(incorrectProduct);
    }

}
