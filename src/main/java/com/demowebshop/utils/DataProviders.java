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
}

