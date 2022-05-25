package ru.vsu.cs.course1.hash.demo;

import ru.vsu.cs.course1.hash.SimpleHashMap;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Program {

    /**
     * Основная функция программы
     *
     * @param args Параметры командной строки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название файла: ");
        String fileName = scanner.nextLine();
        try {
            String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            System.out.println("Встроенная реализация: ");
            solution1(text);
            System.out.println("Через свою реализацию: ");
            solution2(text);
        } catch (Exception e){
            File f = new File(fileName);
            f.createNewFile();
            System.out.println("Файл пуст");
        }
    }

    public static void solution1(String text){
        Map<String, Integer> dict = new HashMap<>();
        text = text.toLowerCase(Locale.ROOT);
        Pattern pattern = Pattern.compile("([?!:;,])");
        String[] txt = text.split("\\s");
        for(int i = 0; i < txt.length - 1; i++){
            Matcher matcher1 = pattern.matcher(txt[i]);
            if(!matcher1.find() && !txt[i].equals("")) {
                Matcher matcher2 = pattern.matcher(txt[i+1]);
                if(matcher2.find()){
                    String newStr = txt[i+1].substring(0, txt[i+1].length() - 1);
                    String str = txt[i] + " " + newStr;
                    if (dict.containsKey(str)) {
                        int x = dict.get(str) + 1;
                        dict.put(str, x);
                    } else {
                        dict.put(str, 1);
                    }
                } else {
                    String str = txt[i] + " " + txt[i + 1];
                    if (dict.containsKey(str)) {
                        int x = dict.get(str) + 1;
                        dict.put(str, x);
                    } else {
                        dict.put(str, 1);
                    }
                }
            }
        }

        int maxValueInMap = (Collections.max(dict.values()));
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());
            }
        }
    }


    public static void solution2(String text){
        text = text.toLowerCase(Locale.ROOT);
        String[] txt = text.split("\\s");
        Map<String, Integer> dict = new SimpleHashMap<>(txt.length + 10);
        Pattern pattern = Pattern.compile("([?!:;,])");
        for(int i = 0; i < txt.length - 1; i++){
            Matcher matcher1 = pattern.matcher(txt[i]);
            if(!matcher1.find() && !txt[i].equals("")) {
                Matcher matcher2 = pattern.matcher(txt[i+1]);
                if(matcher2.find()){
                    String newStr = txt[i+1].substring(0, txt[i+1].length() - 1);
                    String str = txt[i] + " " + newStr;
                    if (dict.containsKey(str)) {
                        int x = dict.get(str) + 1;
                        dict.put(str, x);
                    } else {
                        dict.put(str, 1);
                    }
                } else {
                    String str = txt[i] + " " + txt[i + 1];
                    if (dict.containsKey(str)) {
                        int x = dict.get(str) + 1;
                        dict.put(str, x);
                    } else {
                        dict.put(str, 1);
                    }
                }
            }
        }

        int maxValueInMap = 0;
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            if (entry.getValue()>=maxValueInMap) {
                maxValueInMap = entry.getValue();
            }
        }
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            if (entry.getValue()>=maxValueInMap) {
                System.out.println(entry.getKey());
            }
        }
    }
}
