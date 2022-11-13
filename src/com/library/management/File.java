package com.library.management;

public interface File {
    public abstract void readFile();
    public abstract void writeFile();
    public abstract void convertToObject(String line);
}
