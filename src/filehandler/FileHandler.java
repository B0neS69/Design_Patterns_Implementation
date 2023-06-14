package filehandler;

import dataobjects.Model;
import interfaces.IFileHandler;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler<T> implements IFileHandler<T> {
    public FileHandler() {}
    private final InputHandler ih = new InputHandler();
    @Override
    public void writeInFile(String fileName, Model model) {
        String[] data = ih.addObject(model);
        try {
            FileWriter writer = new FileWriter(fileName,  Charset.forName("Windows-1251"), true);
            writer.write( "\n");
            for (String s : data) {
                writer.write(s + ";"); // дописуємо рядок та розділюємо його символом ";"
            }
            writer.close();
            System.out.println("Дані успішно додано до файлу.");
        } catch (IOException e) {
            System.out.println("Сталася помилка: " + e.getMessage());
        }
    }
    @Override
    public void printTable(List<T> list, int numColumns, Model m) {
        String[] nameCol = m.getFieldName();
        int[] columnWidths = getColumnWidths(list,numColumns, nameCol);

        StringBuilder row = new StringBuilder();
        StringBuilder form = new StringBuilder();
        row.append(String.format("%-" + (5) + "s", " № "));
        form.append("+"+"----");
        for (int i = 0; i < nameCol.length; i++) {
            row.append(String.format("%-" + (columnWidths[i]+4) + "s", "|  "+nameCol[i]));
            form.append("+"+"-".repeat(columnWidths[i]+3));
        }

        System.out.println(form+"+");
        System.out.println(row+"|");
        System.out.println(form+"+");

        for (int i = 0; i < list.size(); i++) {
            String[] rowData = ih.getRowData(list.get(i));
            row = new StringBuilder();
            row.append(String.format("%-" + (5) + "s", " "+(i+1)+". "));
            for (int j = 0; j < rowData.length; j++) {
                row.append(String.format("%-" + (columnWidths[j]+4) + "s", "|  "+rowData[j]));
            }
            System.out.println(row+"|");
            System.out.println(form+"+");
        }
    }

    public boolean checkDate(List<T> list){
        List<T> new_list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate date;
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < list.size(); i++) {
            String[] rowData = ih.getRowData(list.get(i));
            try {
                date = LocalDate.parse(rowData[4], formatter);
                if (date.isBefore(currentDate)) {
                    list.remove(i);
                } else if (date.isEqual(currentDate)) {
                    new_list.add(list.get(i));
                }
            } catch (DateTimeParseException e) {
                // Обробка помилки перетворення дати
                System.out.println("Помилка перетворення дати: " + e.getMessage());
                // Додаткові дії, якщо потрібно
            }
        }

        rewritingFile(list, "Order.txt");
        if (new_list.isEmpty()){
            rewritingFile(new_list, "DeliverToday.txt");
            System.out.println("Сьогодні замовлень немає ");
            return false;
        }else {
            rewritingFile(new_list, "DeliverToday.txt");
            return true;
        }
    }

    private int[] getColumnWidths(List<T> list, int numColumns, String[] nameCol) {
        int[] columnWidths = new int[numColumns];
        for (T element : list) {
            String[] rowData = ih.getRowData(element);
            for (int i = 0; i < rowData.length; i++) {
                if (rowData[i].length() > columnWidths[i]) {
                    columnWidths[i] = rowData[i].length();
                } else if (nameCol[i].length() > columnWidths[i]){
                    columnWidths[i] = nameCol[i].length();
                }
            }
        }
        return columnWidths;
    }
    @Override
    public void removeRow(List<T> list, String fileName) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введіть номер рядка який потрібно видалити: ");
        int num = sc.nextInt();
        num--;
        System.out.println(num+""+list.size());
        if (num < 1 || num > list.size()){
            return;
        }
        list.remove(num);
        rewritingFile(list, fileName);

    }

    private void rewritingFile(List<T> list, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("");
            writer.close();
           // System.out.println("File cleared successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Записуємо дані у файл
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName,  Charset.forName("Windows-1251"), true))) {
            for (T element : list) {
                String[] rowData = ih.getRowData(element);
                StringBuilder row = new StringBuilder();
                for (int i = 0; i < rowData.length; i++) {
                    row.append(rowData[i]+";");
                }
                String str =  String.join(";", row);
                if (list.get(list.size()-1) == element){
                    writer.write(str);
                }else writer.write(str + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}