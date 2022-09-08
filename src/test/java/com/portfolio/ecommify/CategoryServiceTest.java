package com.portfolio.ecommify;

import com.portfolio.ecommify.daos.CategoryDAO;
import com.portfolio.ecommify.services.CategoryService;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryService sut;
    private final CategoryDAO mockCatDao = mock(CategoryDAO.class);

    @Before
    public void setup() {
        sut = new CategoryService(mockCatDao);
    }


    @Test(expected = InvalidInputException.class)
    public void test_catgoryNameInput_invalidInputGivenStringWithEmptyString() {
        // Arrange
        CategoryService spiedSut = Mockito.spy(sut);
        String incorrectProduct = " ";

        when(mockCatDao.isValidInput(incorrectProduct)).thenReturn(true);

        // Act
        sut.isValidInput(incorrectProduct);
    }

}
