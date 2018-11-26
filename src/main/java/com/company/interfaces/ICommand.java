package com.company.interfaces;

public interface ICommand {
    boolean invoke(String name, String[] args);

    String getName();

    String getDescription();
}
