package interfaces;

import dataobjects.Model;

import java.util.List;

public interface IFileHandler<T> {
    void writeInFile(String filename, Model model);
    void printTable(List<T> list, int numColumns, Model m);
    void removeRow(List<T> list, String filename);
    boolean checkDate(List<T> list);
}
