package sg.eud.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
       String dirPath="data2";

       File newDirectory =new File(dirPath);

       if (newDirectory.exists()){
        System.out.println("Directory already exist");
       }else{
        newDirectory.mkdir();
       }
    }
}
