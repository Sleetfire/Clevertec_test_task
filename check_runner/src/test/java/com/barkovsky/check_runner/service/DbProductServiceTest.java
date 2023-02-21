package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.ConversionException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.db_repository.api.IDbProductRepository;
import com.barkovsky.check_runner.repository.db_repository.entity.ProductEntity;
import com.barkovsky.check_runner.service.api.IProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DbProductServiceTest {

    @MockBean
    private IDbProductRepository productRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProduct")
    @DisplayName("Adding product to the db storage")
    void checkAddProductToDatabaseStorageShouldReturnProduct(Product product) {
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(productEntity);

        Product dbProduct = this.productService.add(product);

        Assertions.assertThat(dbProduct).isEqualTo(product);
    }

    @Test
    @DisplayName("Adding null to the db product storage")
    void checkAddProductToDatabaseStorageShouldThrowConversionException() {
        Assertions.assertThatThrownBy(() -> productService.add(null))
                .isInstanceOf(ConversionException.class)
                .hasMessageContaining("Essence conversion error");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProduct")
    @DisplayName("Getting product by id from the db storage")
    void checkGetProductByIdShouldReturnProduct(Product product) {
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        Mockito.when(this.productRepository.findById(1L)).thenReturn(Optional.ofNullable(productEntity));

        Product dbProduct = this.productService.get(1L);

        Assertions.assertThat(dbProduct).isEqualTo(product);
    }

    @Test
    @DisplayName("Getting product by id from the db storage with EssenceNotFoundException")
    void checkGetProductByIdShouldTrowEssenceNotFoundException() {
        Mockito.when(this.productRepository.findById(1000L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> productService.get(1000L))
                .isInstanceOf(EssenceNotFoundException.class)
                .hasMessageContaining("Product with id: 1000 doesn't exist");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProductList")
    @DisplayName("Getting product's list from the db storage")
    void checkGetAllProductsShouldReturnListSizeMoreThenZero(List<Product> products) {
        List<ProductEntity> productEntityList = new ArrayList<>();
        products.forEach(product -> productEntityList.add(this.conversionService.convert(product, ProductEntity.class)));
        Mockito.when(this.productRepository.findAll()).thenReturn(productEntityList);

        int size = this.productService.get().size();

        Assertions.assertThat(size).isPositive();
    }

    @Test
    @DisplayName("Getting product's list from the db storage with EssenceNotFoundException")
    void checkGetAllProductsShouldThrowEssenceNotFoundException() {
        Mockito.when(this.productRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThatThrownBy(() -> this.productService.get())
                .isInstanceOf(EssenceNotFoundException.class)
                .hasMessageContaining("Products don't exists");
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