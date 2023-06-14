package menu;

import data.*;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class Menu {
    String encoding = "Cp1251";

    Scanner sc = new Scanner(new BufferedInputStream(System.in), encoding);
   // Scanner sc = new Scanner(System.in);
    public void startMenu() {
        clearConsole();

        BakeryFacade bakeryFacade;
        String[] model_name = {"Assortment", "Customers", "Supplier", "Storage", "SaleStatistics", "Employee", "Order"};
        String[] table_name = {"����������", "�볺���", "�������������", "�����", "���������� �������", "�����������", "����������"};
        String skip = "+"+"-".repeat(45)+"+";

        System.out.println(skip);
        System.out.println(String.format("%-" + 45 + "s", "|  ������� ��� � ����� ������ ���������")+ " |");
        System.out.println(skip);
        for (int i = 0; i < model_name.length; i++) {
            System.out.println(String.format("%-" + 45 + "s", "| "+(i+1)+". "+table_name[i])+" |");
            System.out.println(skip);
        }

        System.out.println(String.format("%-" + 45 + "s", "| "+(8)+". ���������� �� ������� ���������")+" |");
        System.out.println(skip);
        System.out.println(String.format("%-" + 45 + "s", "| "+(0)+". ����� ")+" |");
        System.out.println(skip);
        int num = sc.nextInt()-1;
        if(num == -1) {
            System.exit(0);
        }
        if(num == 7){
            bakeryFacade = new BakeryFacade(model_name[6]);
            bakeryFacade.deliverToday();
            System.out.println("1. ����������� �� ����");
            System.out.println("0. �����");
            int numm = sc.nextInt();
            if(numm == 1){
                startMenu();
            }else {return;}
        }else if(num < 0 || num > table_name.length){
            bakeryFacade = new BakeryFacade<>(model_name[num]);
            bakeryFacade.deleteOldDelivery();
            System.out.println("������� ����������� ��������. �������� ������");
            startMenu();
        }else selectAction(model_name[num], table_name[num]);
    }

    public void selectAction(String nameT, String name) {
        BakeryFacade bakeryFacade = new BakeryFacade(nameT);
        String skip = "+"+"-".repeat(60)+"+";
        clearConsole();
        System.out.println(skip);
        while (true) {
            bakeryFacade.loadDataList();
            clearConsole();

            System.out.println(
                    skip + "\n" + String.format("%-" + 60 + "s", "| ������� �� "                     )  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 1. ������� ������� "         + name)  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 2. ������ ��� �� ������� "  + name)  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 3. �������� ��� � ������� " + name)  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 4. ����������� ������� "     + name)  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 5. ����� ����� � ������� "   + name)  + " | \n" +
                    skip + "\n" + String.format("%-" + 60 + "s", "| 6. �����"                          )  + " | \n" +
                    skip);
            int choice = sc.nextInt();
            sc.nextLine(); // �������� ����� ���� �������� �����
            switch (choice) {
                case 1:
                    clearConsole();
                    bakeryFacade.outputDataList();
                    break;
                case 2:
                    bakeryFacade.addDataToList();
                    break;
                case 3:
                    bakeryFacade.removeDataFromList();
                    break;
                case 4:
                    bakeryFacade.sortDataListByField();
                    bakeryFacade.outputDataList();
                    break;
                case 5:
                    bakeryFacade.filterDataList();
                    bakeryFacade.outputDataList();
                    break;
                case 6:
                    startMenu();
                    return;
                default:
                    System.out.println("������������ ����. ��������� �� ���.");
                    continue; // ���������� �� �������� �������� �����
            }
            if(choice != 1 && choice != 4 && choice != 5) {
                clearConsole();
                bakeryFacade.loadDataList();
                bakeryFacade.outputDataList();
            }

            // ��������� ���� � ������� ��������� ��
            System.out.println("������ ����������? (���/�)");
            String answer = (sc.nextLine()).toLowerCase();

            if (answer.equals("�")) {
                clearConsole();
                startMenu();
            }
        }

    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("�� ������� �������� �������: " + e.getMessage());
        }
    }
}
