package com.springtest.demo.service;

import com.springtest.demo.model.Product;
import com.springtest.demo.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    @InjectMocks
    ProductService productService = new ProductService();

    @Mock
    ProductRepository mockedProductRepository;

    private int id1;
    private int id2;
    private int id3;
    private int sizeBeforeTest;

    @BeforeAll
    static void beforeAll(){

    }

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        Product product1 = new Product(1,"navn1",145);
        Product product2 = new Product(2,"navn2",245);
        Product product3 = new Product(3,"navn3",345);
        Product product4 = new Product(4,"navn4",445);
        ArrayList<Product> listWithAll = new ArrayList<>();
        listWithAll.add(product1);
        listWithAll.add(product2);
        listWithAll.add(product3);
        listWithAll.add(product4);

        //faker ProductRepository (CRUDS)
        Mockito.when(mockedProductRepository.read(1)).thenReturn(product1);
        Mockito.when(mockedProductRepository.read(2)).thenReturn(product2);
        Mockito.when(mockedProductRepository.read(3)).thenReturn(product3);
        Mockito.when(mockedProductRepository.read(4)).thenReturn(product4);
        Mockito.when(mockedProductRepository.readAll()).thenReturn(listWithAll);

        //        List<Product> a = productService.readAll();
//
//        //Opretter 3 products og fanger deres id's disse
//        // kan bruges til test af create, update, delete, read
//        Product productObj1 = new Product("Kattemad1",10.11);
//        productService.create(productObj1);
//        id1 = (int) a.get(a.size()-1).getIdProducts();

    }

    @Test
    void testCreate() {
        List<Product> a = productService.readAll();

        //Product som indsættes
        Product expected = new Product("Kattemad1",10.11);

        //product indsættes i database
        productService.create(expected);

        //Henter id af sidste product i liste
        id1 = (int) a.get(a.size()-1).getId();

        //Henter sidste product object fra database
        Product actual = productService.read(id1);

        //Assert
        assertEquals(expected.getName(), actual.getName());





    }

    @Test
    void testDelete() {
    }

    @Test
    void testUpdate() {


    }

    @Test
    void testReadAll() {

        List<Product> products = new ArrayList<>();
        products= productService.readAll();
        //int actualNumberProducts = products.size();
    }

    @Test
    void testRead() {

        String name = productService.read(1).getName();
        List<Product> list = productService.readAll();

//        assertEquals("navn1",name);
        assertEquals(4,productService.readAll().size());
    }
}