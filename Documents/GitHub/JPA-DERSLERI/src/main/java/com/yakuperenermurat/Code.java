package com.yakuperenermurat;


import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="code_id" , columnDefinition = "serial")
    private int id;

    @Column(name = "code_group",nullable = false)
    private String group;

    @Column(name = "code_serial",nullable = false)
    private String serial;


    @OneToOne(mappedBy = "code")
    private Product product;

    public Code() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Code.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("group='" + group + "'")
                .add("serial='" + serial + "'")
                .toString();
    }
}
