package com.example.devesh.place_my_meal.Models;

/**
 * Created by devesh on 10/2/17.
 */

public class OffDatabase extends Table{

    public static final String TABLE_NAME = "myOrder";

    public static final String TABLE_CREATE_CMD = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
            +LBR
            +Columns.MENU_ID + TYPE_INT_PK + COMMA
            +Columns.OUTLET_ID + TYPE_INT + COMMA
            +Columns.QUANTITY + TYPE_INT + COMMA
            +Columns.PRICE + TYPE_INT
            +Columns.NAME + TYPE_TEXT
            +RBR + ";" ;

    public interface Columns{
        String MENU_ID = "menu_id";
        String OUTLET_ID = "outlet_id";
        String QUANTITY = "quantity";
        String PRICE = "price";
        String NAME = "name";
    }
}
