package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.OrderProduct;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.memory_repository.MemoryProductRepository;
import com.barkovsky.check_runner.repository.memory_repository.api.IProductRepository;
import com.barkovsky.check_runner.service.api.IProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemoryProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(this.productRepository);
        //Mockito.clearAllCaches();
        //Mockito.clearInvocations();
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProduct")
    @DisplayName("Adding product to the memory storage")
    void checkAddShouldReturnProduct(Product product) {
//        try (MockedStatic<MemoryProductRepository> productRepositoryMock = Mockito.mockStatic(MemoryProductRepository.class)) {
//            productRepositoryMock.when(MemoryProductRepository::getInstance).thenReturn(productRepository);
//
//            IProductService productService = MemoryProductService.getInstance();
//
//            when(productRepository.add(product)).thenReturn(product);
//            //when(productRepository.add(product)).thenAnswer(invocation -> invocation.getArgument(0));
//
//            Product productFromDb = productService.add(product);
//            System.out.println(productFromDb);
//            Assertions.assertThat(productFromDb).isEqualTo(product);
//        }
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProduct")
    @DisplayName("Getting product by id from memory storage")
    void checkGetShouldReturnProduct(Product product) {
//        try (MockedStatic<MemoryProductRepository> productRepositoryMock = Mockito.mockStatic(MemoryProductRepository.class)) {
//            productRepositoryMock.when(MemoryProductRepository::getInstance).thenReturn(productRepository);
//
//            IProductService productService = MemoryProductService.getInstance();
//
//            //doReturn(product).when(this.productRepository).get(1);
//            when(productRepository.get(2)).thenReturn(product);
//
//            Product productFromDb = productService.get(2);
//            Assertions.assertThat(productFromDb).isEqualTo(product);
//        }
    }

    @Test
    @DisplayName("Getting product by id from memory storage (checking throwing EssenceNotFoundException")
    void checkGetShouldThrowEssenceNotFoundException() {
        try (MockedStatic<MemoryProductRepository> productRepositoryMock = Mockito.mockStatic(MemoryProductRepository.class)) {

            productRepositoryMock.when(MemoryProductRepository::getInstance).thenReturn(productRepository);

            IProductService productService = MemoryProductService.getInstance();

            when(productRepository.get(4)).thenReturn(null);

            Assertions.assertThatThrownBy(() -> {
                        productService.get(4);
                    }).isInstanceOf(EssenceNotFoundException.class)
                    .hasMessageContaining("Product with id: 4 doesn't exist");
        }
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProductList")
    @DisplayName("Getting product's list from memory storage")
    void checkGetAllShouldReturnTrue(List<Product> productList) {
        try (MockedStatic<MemoryProductRepository> productRepositoryMock = Mockito.mockStatic(MemoryProductRepository.class)) {
            productRepositoryMock.when(MemoryProductRepository::getInstance).thenReturn(productRepository);

            IProductService productService = MemoryProductService.getInstance();

            when(productRepository.get()).thenReturn(productList);

            List<Product> productsFromDb = productService.get();
            Assertions.assertThat(productsFromDb).isNotEmpty();
        }
    }

    @Test
    @DisplayName("Getting product's list from empty memory storage")
    void checkGetAllShouldReturnEmptyList() {
        try (MockedStatic<MemoryProductRepository> productRepositoryMock = Mockito.mockStatic(MemoryProductRepository.class)) {
            productRepositoryMock.when(MemoryProductRepository::getInstance).thenReturn(productRepository);

            IProductService productService = MemoryProductService.getInstance();

            when(productRepository.get()).thenReturn(Collections.emptyList());

            List<Product> productsFromDb = productService.get();
            Assertions.assertThat(productsFromDb).isEmpty();
        }
    }

    static Stream<Product> getProduct() {
        Product product = Product.Builder.createBuilder()
                .setId(1)
                .setDescription("test-product1")
                .setPrice(BigDecimal.valueOf(1.0))
                .setDiscount(false)
                .build();
        return Stream.of(product);
    }

    static Stream<List<Product>> getProductList() {

        Product product1 = Product.Builder.createBuilder()
                .setId(1)
                .setDescription("test-product1")
                .setPrice(BigDecimal.valueOf(1.0))
                .setDiscount(false)
                .build();

        Product product2 = Product.Builder.createBuilder()
                .setId(2)
                .setDescription("test-product2")
                .setPrice(BigDecimal.valueOf(2.5))
                .setDiscount(true)
                .build();

        Product product3 = Product.Builder.createBuilder()
                .setId(3)
                .setDescription("test-product3")
                .setPrice(BigDecimal.valueOf(0.7))
                .setDiscount(false)
                .build();

        Product product4 = Product.Builder.createBuilder()
                .setId(4)
                .setDescription("test-product4")
                .setPrice(BigDecimal.valueOf(33))
                .setDiscount(true)
                .build();

        List<Product> productList = List.of(product1, product2, product3, product4);
        return Stream.of(productList);
    }
}