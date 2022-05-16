package Flattener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Flatten {

    public static void main(String[] args) {
        Mapper map = new Mapper();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the file path");
        String filePath = scan.nextLine();
        try {
            FileInputStream json = new FileInputStream(filePath);
            String jsonStr = IOUtils.toString(json, "UTF-8");
            System.out.println(map.read(jsonStr));
        }catch (NullPointerException e){
            System.out.println(Errors.SYSTEM_FAILURE);
        }catch (JsonProcessingException e) {
            System.out.println(Errors.INVALID_JSON);
        }catch (IOException e) {
            System.out.println(Errors.SYSTEM_FAILURE);
        }
    }
}
