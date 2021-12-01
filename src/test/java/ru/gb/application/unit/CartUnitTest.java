package ru.gb.application.unit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gb.controller.CartController;
import ru.gb.repository.CartRepository;

public class CartUnitTest {

    @Test
    void thenThrowCartUnitTEst(){
        CartRepository repository = Mockito.mock(CartRepository.class);
        Mockito.when(repository.findById(5L))
                .thenThrow(new IllegalArgumentException());
    }

    @Test
    void verifyCartUnitTEst(){
        CartController controller = Mockito.mock(CartController.class);
       // Mockito.verify(controller).findById(Mockito.any());
        Mockito.verify(controller, Mockito.after(10000).times(1))
                .findById(Mockito.any());
    }
}
