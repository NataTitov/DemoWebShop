package com.demowebshop.utils;

import com.demowebshop.data.UserData;
import com.demowebshop.models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> addQuantityProductToCartNegativeTest() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"0"});
        list.add(new Object[]{""});
        list.add(new Object[]{"-1"});
        list.add(new Object[]{"25897456321456997744"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createNewContactWithCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/DemoWebshopData.csv")));

        String line = reader.readLine();
        while (line != null) {

            String[] split = line.split(",");

            list.add(new Object[]{new User()
                    .setGender(split[0])
                    .setName(split[1])
                    .setLastName(split[2])
                    .setEmail(split[3])
                    .setPassword(split[4])});
            line = reader.readLine();
        }
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addGiftCardToCartPositiveTest() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Thomas", "t@mail.com", "test"});
        list.add(new Object[]{"John", "j@mail.com", "test"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addGiftCardToCartNegativeTest() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"", "t@mail.com", "test"});
        list.add(new Object[]{"John", "", "test"});
        list.add(new Object[]{"", " ", "test"});
        return list.iterator();
    }
}

