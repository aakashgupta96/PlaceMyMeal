package com.example.devesh.place_my_meal.Models;

/**
 * Created by devesh on 10/2/17.
 */

public class Company {


    public static class Items {


        private Integer id;
        private String name;

        public Items(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
