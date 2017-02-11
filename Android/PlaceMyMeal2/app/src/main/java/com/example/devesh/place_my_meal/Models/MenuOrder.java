package com.example.devesh.place_my_meal.Models;

/**
 * Created by devesh on 11/2/17.
 */

public class MenuOrder {

    public static class port {

        String name;

        Integer id;
        Integer company_id;
        Integer price;

        public port(String name, Integer id, Integer company_id, Integer price) {
            this.name = name;
            this.id = id;
            this.company_id = company_id;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getcompany_id() {
            return company_id;
        }

        public void setcompany_id(Integer company_id) {
            this.company_id = company_id;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
