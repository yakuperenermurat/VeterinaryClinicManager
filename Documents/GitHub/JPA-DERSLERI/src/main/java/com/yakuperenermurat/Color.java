package com.yakuperenermurat;


import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id" , columnDefinition = "serial")
    private int id;

    @Column(name = "color_name" , nullable = false)
    private String name;


    @ManyToMany(mappedBy = "colorList")
    private List<Product> productList;

    public Color() {
    }

    public Color(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Color.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
