package ru.gb.application.integrationTest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import ru.gb.controller.CategoryController;
import ru.gb.entity.Category;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CategoryRestControllerIntegrationTest {

    @MockBean
    private CategoryController categoryController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void categoryControllerTest() {
        ResponseEntity<Category> category = restTemplate.getForEntity("/categories", Category.class);
        Assertions.assertThat(category.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void categoryFindAllTest() {
        List<Category> categories = new ArrayList<>();
        Category category = new Category(1, "TestCat");
        categories.add(category);
        Model model = null;
        BDDMockito.given(categoryController.findAll(categories, model))
                .willReturn("/category-all");
        ResponseEntity<Category> responseEntity = restTemplate.getForEntity("/category-all", Category.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
