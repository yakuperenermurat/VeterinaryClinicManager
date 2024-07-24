package com.yakuperenermurat;

import jakarta.persistence.*;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("market");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();



        transaction.begin();
/*
        //Supplier Ekleme
        Supplier supplier = new Supplier();
        supplier.setAddress("Adres");
        supplier.setCompany("Patika");
        supplier.setContact("50234234523");
        supplier.setMail("info@patika.dev");
        supplier.setPerson("Mustafa Çetindağ");
        entityManager.persist(supplier);

        //Category ekleme
        Category category = new Category();
        category.setName("Telefonlar");
        entityManager.persist(category);

        //Code ekleme
        Code code = new Code();
        code.setGroup("11123");
        code.setSerial("44456");
        entityManager.persist(code);

        //Ürün ekleme
        Product product = new Product();
        product.setName("Iphone 15pro");
        product.setPrice(1234);
        product.setStock(100);
        product.setCode(code);
        product.setSupplier(supplier);
        product.setCategory(category);

        entityManager.persist(product);
        System.out.println(product.toString());


        Color blue = new Color("blue");
        Color red = new Color("red");
        Color yellow = new Color("yellow");

        entityManager.persist(blue);
        entityManager.persist(red);
        entityManager.persist(yellow);


        List<Color> colorList = product.getColorList();
        colorList.add(blue);
        colorList.add(red);
        product.setColorList(colorList);

        entityManager.persist(product);

 */
/*   Product product =entityManager.find(Product.class, 1);
        System.out.println(product.getName());*/
/*
        Color color = entityManager.find(Color.class, 1);
        System.out.println(color.getProductList().toString());

 */
/*
        Code code = new Code();
        code.setSerial("2234");
        code.setGroup("1234");


        Product product = new Product();
        product.setName("Test");
        product.setStock(10);
        product.setPrice(123);
        product.setCategory(entityManager.find(Category.class,1));
        product.setSupplier(entityManager.find(Supplier.class,1));
        product.setCode(code);

        entityManager.persist(product);

 */
        // Query getAllCategories = entityManager.createQuery("SELECT cat FROM Category cat ");
       /*
        TypedQuery<Category> getAllCategoriesQuery = entityManager.createQuery("SELECT cat FROM Category cat WHERE cat.id = :id", Category.class);
        getAllCategoriesQuery.setParameter("id",3);
        Category category = getAllCategoriesQuery.getSingleResult();
        System.out.println(category.getName());

        */
        /*
        List<Category> categories = getAllCategoriesQuery.getResultList();
        for (Category category : categories) {
            System.out.println(category.getName());
        }
         */

        TypedQuery<String> prodQuery = entityManager.createQuery("SELECT p.name FROM Product p WHERE p.price <10 ", String.class);
        List<String> productNameList = prodQuery.getResultList();
        for (String string : productNameList) {
            System.out.println(string);
        }
        transaction.commit();


    }
}