package com.example.devesh.place_my_meal.Models;

/**
 * Created by devesh on 11/2/17.
 */

public class MenuOrder {

    public static class port {

        String name;

        Integer id;
        Integer menu_id;
        Integer price;

        public port(String name, Integer id, Integer menu_id, Integer price) {
            this.name = name;
            this.id = id;
            this.menu_id = menu_id;
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

        public Integer getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(Integer menu_id) {
            this.menu_id = menu_id;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
