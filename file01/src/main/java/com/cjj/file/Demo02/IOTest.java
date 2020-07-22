package com.cjj.file.Demo02;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * 输入流和输出流
 * xiangjiaoyun
 * author:chenjianjie
 * Date:2020/7/22
 * Time:0:51
 */
public class IOTest {
    public static void main(String[] args) throws Exception {
        //字节输出流
        //test_FileOutputStream();
        //字节输入流
        //test_FileInputStream();
        //复制文件
        //copy();
        //字符输入流
        //test_FileReader();
        //字符输出流
        //test_FileWriter();
        //properties
        //test_Proterties();
        //缓冲流
        //test_Buff();
        //转换流
        //test_Change();
        //序列化
        test_Object();
    }


    public static void test_FileOutputStream() throws Exception {
        File file = new File("F:\\test\\test_FileOutputStream.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        String str = "123abc哈哈";
        byte[] bytes = str.getBytes();
        fileOutputStream.write("\r\n".getBytes());
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public static void test_FileInputStream() throws Exception {
        File file = new File("F:\\test\\test_FileOutputStream.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[2];
        int len  = 0;
        while((len = fileInputStream.read(bytes)) != -1){
            System.out.println(len);
            String str = new String(bytes,0,len);
            System.out.println(str);
        }
        fileInputStream.close();
    }

    public static void copy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\test\\test_FileOutputStream.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\test\\test_FileOutputStream_copy.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0,len);
        }
        fileOutputStream.close();
        fileInputStream.close();
    }

    public static void test_FileReader() throws IOException {
        FileReader fileReader = new FileReader("F:\\test\\test_FileOutputStream.txt");
        int len = 0;
        char[] chars = new char[1024];
        while((len = fileReader.read(chars)) != -1){
            String str = new String(chars, 0 ,len);
            System.out.println(str);
        }
        fileReader.close();
    }

    public static void test_FileWriter() throws IOException{
        FileWriter fileWriter = new FileWriter("F:\\test\\test_FileWriter.txt",true);
        char[] chars = "dasda".toCharArray();
        fileWriter.write(chars);
        String str = "hahadasa圣埃蒂安hdsa12354";
        fileWriter.write(str);
        fileWriter.close();
    }

    public static void test_Proterties() throws IOException {

        FileWriter fileWriter = new FileWriter("F:\\test\\test_Proterties.txt");

        Properties properties = new Properties();
        properties.setProperty("哈哈1", "11141");
        properties.setProperty("哈哈2", "11142");
        properties.setProperty("哈哈3", "11143");

        properties.store(fileWriter, "test_Proterties");

        FileReader fileReader = new FileReader("F:\\test\\test_Proterties.txt");
        Properties properties1 = new Properties();
        properties1.load(fileReader);
        Set<String> strings = properties1.stringPropertyNames();
        for (String string : strings) {
            System.out.println(properties1.getProperty(string));
        }

    }

    public static void test_Buff() throws IOException {

        //字节缓冲输入流
        FileInputStream fileInputStream = new FileInputStream("F:\\test\\test_FileOutputStream.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] bytes = new byte[1024];
        bufferedInputStream.read(bytes);
        System.out.println(new String(bytes));
        bufferedInputStream.close();
        fileInputStream.close();

        //字节缓冲输出流
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\test\\test_Buff.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        String str = "字符缓冲输出流";
        bufferedOutputStream.write(str.getBytes());
        bufferedOutputStream.close();
        fileOutputStream.close();


        //字符输入流
        FileReader fileReader = new FileReader("F:\\test\\test_FileOutputStream.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        char[] chars = new char[1024];
        bufferedReader.read(chars);
        System.out.println(new String(chars));
        bufferedReader.close();
        fileReader.close();

        //字符输出流
        FileWriter fileWriter = new FileWriter("F:\\test\\test_Buff_writer.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String str1 = "字符输出流";
        bufferedWriter.write(str1);
        bufferedWriter.close();
        fileWriter.close();

    }

    public static void test_Change() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("F:\\test\\test_Change.txt"), "utf-8");
        String str = "字节转换流";
        outputStreamWriter.write(str);
        outputStreamWriter.close();


        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("F:\\test\\test_Change.txt"),"utf-8");
        char[] chars = new char[1024];
        inputStreamReader.read(chars);
        System.out.println(new String(chars));
        inputStreamReader.close();

    }


    public static void test_Object() throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("F:\\test\\test_Object.txt"));
        Person person = new Person("张三", "18");
        Person person2 = new Person("李四", "22");
        objectOutputStream.writeObject(person);
        objectOutputStream.writeObject(person2);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("F:\\test\\test_Object.txt"));
        Person person1 = (Person) objectInputStream.readObject();
        Person person3 = (Person) objectInputStream.readObject();
        System.out.println(person1.age);
        System.out.println(person1.name);
        System.out.println(person3.age);
        System.out.println(person3.name);
    }
}

class Person implements Serializable{

    String name;
    String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }
}