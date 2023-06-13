package filehandler;

import dataobjects.Model;

import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

class InputHandler{
    public static ArrayList<Integer> getIndexesOfIntFields(Model model){
        Field[] fields = model.getClass().getDeclaredFields();
        ArrayList<Integer> indexesOfIntFields = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].getType().getName().equals("int")){
                indexesOfIntFields.add(i);
            }
        }
        return indexesOfIntFields;
    }
    public static boolean checkIntInput(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String[] addObject(Model model) {
        String encoding = "Cp1251";

        Scanner sc = new Scanner(new BufferedInputStream(System.in), encoding);

        String[] name = model.getFieldName();
        String[] add = new String[name.length];
        ArrayList<Integer> IndexesOfIntFields = getIndexesOfIntFields(model);
        String str;

        System.out.println("\n"+"-".repeat(30));

        for (int i = 0; i < name.length; i++) {
            if (IndexesOfIntFields.contains(i)){
                System.out.println("¬вед≥ть " + name[i] + ": ");
                str = sc.nextLine();
                if (checkIntInput(str)) {
                    add[i] = str;
                } else i--;
            }else {
                System.out.println("¬вед≥ть "+name[i]+": ");
                str = sc.nextLine();
                add[i] = str;
            }
            System.out.println("-".repeat(30));
        }
        return add;
    }
    public String[] getRowData(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        String[] rowData = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                Object value = fields[i].get(object);
                rowData[i] = value != null ? value.toString() : "";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return rowData;
    }
}