package com.demowebshop.data;

public class UserData {
    static int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

    public static final String EMAIL_REG = "test" + i + "@test.com";
    public static final String EMAIL_LOGIN = "pataha@gmx.com";
    public static final String EMAIL_REG_NEGATIVE = "test101@test.com";
    public static final String PASSWORD_REG_LOGIN = "vgBH123456!";
    public static final String NAME_REG = "John";
    public static final String LASTNAME_REG = "Snow";
    public static final String NAME_REG_NEGATIVE = "John";
    public static final String LASTNAME_REG_NEGATIVE = "Snow";
}
