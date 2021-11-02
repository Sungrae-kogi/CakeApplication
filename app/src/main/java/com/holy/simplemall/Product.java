package com.holy.simplemall;

import java.util.Objects;


//Data담을 모델클래스
public class Product {

    private final String title;
    private final int resource;

    public Product(String title, int resource) {
        this.title = title;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public int getResource() {
        return resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;
        return resource == product.resource && title.equals(product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, resource);
    }
}
