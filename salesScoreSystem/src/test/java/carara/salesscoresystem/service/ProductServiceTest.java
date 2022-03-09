package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.ProductDto;
import carara.salesscoresystem.exception.EntityNotFoundException;
import carara.salesscoresystem.model.Product;
import carara.salesscoresystem.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductService.class})
@ActiveProfiles({"docker"})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void testInsertProduct() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        when(this.productRepository.save((Product) any())).thenReturn(product);

        // Act and Assert
        assertSame(product, this.productService.insertProduct(new ProductDto("Name", 10.0)));
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testInsertProduct2() {
        // Arrange
        when(this.productRepository.save((Product) any())).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> this.productService.insertProduct(new ProductDto("Name", 10.0)));
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        Optional<Product> ofResult = Optional.of(product);

        Product product1 = new Product();
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(10.0);
        when(this.productRepository.save((Product) any())).thenReturn(product1);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(product1, this.productService.updateProduct(123L, new ProductDto("Name", 10.0)));
        verify(this.productRepository).save((Product) any());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testUpdateProduct2() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        Optional<Product> ofResult = Optional.of(product);
        when(this.productRepository.save((Product) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> this.productService.updateProduct(123L, new ProductDto("Name", 10.0)));
        verify(this.productRepository).save((Product) any());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testUpdateProduct3() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> this.productService.updateProduct(123L, new ProductDto("Name", 10.0)));
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(this.productRepository).delete((Product) any());
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertEquals("Product with id 123 was deleted successfully.", this.productService.deleteProduct(123L));
        verify(this.productRepository).findById((Long) any());
        verify(this.productRepository).delete((Product) any());
    }

    @Test
    void testDeleteProduct2() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        Optional<Product> ofResult = Optional.of(product);
        doThrow(new EntityNotFoundException("An error occurred")).when(this.productRepository).delete((Product) any());
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> this.productService.deleteProduct(123L));
        verify(this.productRepository).findById((Long) any());
        verify(this.productRepository).delete((Product) any());
    }

    @Test
    void testDeleteProduct3() {
        // Arrange
        doNothing().when(this.productRepository).delete((Product) any());
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> this.productService.deleteProduct(123L));
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testFindById() {
        // Arrange
        Product product = new Product();
        product.setId(123L);
        product.setName("Name");
        product.setPrice(10.0);
        Optional<Product> ofResult = Optional.of(product);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(product, this.productService.findById(123L));
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testFindById2() {
        // Arrange
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> this.productService.findById(123L));
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testFindById3() {
        // Arrange
        when(this.productRepository.findById((Long) any())).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> this.productService.findById(123L));
        verify(this.productRepository).findById((Long) any());
    }
}

