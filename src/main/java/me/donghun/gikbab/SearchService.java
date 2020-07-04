package me.donghun.gikbab;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService {

    public static void main(String[] args) {
        SearchService searchService = new SearchService();
        String test = "쓰1";
        if(searchService.isExist(test)){
            System.out.println(test + " 존재하는 메뉴");
        }
        else{
            System.out.println(test + " 존재하지 않는 메뉴");
        }
        if(searchService.hasPicture(test))
            System.out.println(test + " 제보된 사진 존재");
        else{
            System.out.println(test + " 제보된 사진 존재하지 않음");
        }
    }

    public boolean isExist(String input) {
        File file = new File("food.txt");
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null; // unicode를 읽을 수 있다
        try {
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        while(true){
            try {
                if ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    if(line.equals(input)) {
                        return true;
                    }
                }
                else
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean hasPicture(String input) {
        List<String> listOfFilenameWithoutExtension = getListOfFilenameWithoutExtension();
        for(String filename : listOfFilenameWithoutExtension){
            if(input.equals(filename)){
                return true;
            }
        }
        return false;
    }

    private List<String> getListOfFilenameWithoutExtension() {
        File folder = new File("upload-dir");
        File[] listOfFiles = folder.listFiles();
        List<String> listOfFilenameWithoutExtension = new ArrayList<>();
        Arrays.stream(listOfFiles).forEach(c -> listOfFilenameWithoutExtension.add(c.getName().substring(0, c.getName().indexOf('.'))));
        return listOfFilenameWithoutExtension;
    }
}
