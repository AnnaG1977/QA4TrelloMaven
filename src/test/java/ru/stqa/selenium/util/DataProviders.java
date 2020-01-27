package ru.stqa.selenium.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/LoginIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderPasswordIncorrect() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/PasswordIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"login1", "psw1"});
        data.add(new Object[]{"login2", "psw2"});
        data.add(new Object[]{"login3", "psw3"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();
        for(int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomPassword(), this.generateRandomName()});
        }
        return data.iterator();
    }

    private Object generateRandomPassword() {
        return "pass" + (new Random()).nextInt();
    }
    private Object generateRandomName() {
        return "demo" + (new Random()).nextInt()+"@gmail.com";
    }
    @DataProvider
    public Iterator<Object[]> dataProviderText() {
        List<Object[]> data = new ArrayList();
        for(int i = 0; i < 2; ++i) {
            data.add(new Object[]{generateRandomListName(),generateRandomListName()});
        }
        return data.iterator();
    }

    private Object generateRandomListName() {
        String name = genRandomString(4);
        return name +"  -  " + (new Random()).nextInt();
    }
    public static String genRandomString(int num){
        String str = "";
        int number;
        Random gen = new Random();
        for(int i=0; i<num; i++){
            number =  gen.nextInt('z' - '!' +1);
            str = str + (char)number;
        }
        return str;
    }

}

