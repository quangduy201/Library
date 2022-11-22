package com.library.management;

public interface File {
    void readFile();
    void writeFile(Object[] objects, boolean append);
    void convertToObject(String line);
}
