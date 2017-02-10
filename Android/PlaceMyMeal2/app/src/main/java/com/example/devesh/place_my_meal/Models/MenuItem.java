package com.example.devesh.place_my_meal.Models;

/**
 * Created by devesh on 10/2/17.
 */

public class MenuItem {

    public static class Food{
        Integer MenuID;
        Integer CompanyID;
        Integer Quantity;
        Integer Value;

        public Food(Integer menuID, Integer companyID, Integer quantity, Integer value) {
            MenuID = menuID;
            CompanyID = companyID;
            Quantity = quantity;
            Value = value;
        }

        public Integer getMenuID() {
            return MenuID;
        }

        public void setMenuID(Integer menuID) {
            MenuID = menuID;
        }

        public Integer getCompanyID() {
            return CompanyID;
        }

        public void setCompanyID(Integer companyID) {
            CompanyID = companyID;
        }

        public Integer getQuantity() {
            return Quantity;
        }

        public void setQuantity(Integer quantity) {
            Quantity = quantity;
        }

        public Integer getValue() {
            return Value;
        }

        public void setValue(Integer value) {
            Value = value;
        }
    }

}
