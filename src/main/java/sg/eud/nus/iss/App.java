package sg.eud.nus.iss;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
       String dirPath="data2";

       File newDirectory =new File(dirPath);

       if (newDirectory.exists()){
        System.out.println("Directory already exist");
       }else{
        newDirectory.mkdir();
       }

       Cookie cookie =new Cookie();
       cookie.readCookieFile();
       cookie.showCookies();


       ServerSocket ss= new ServerSocket(7000);
       Socket s= ss.accept();

       try(InputStream is= s.getInputStream()){
            BufferedInputStream bis= new BufferedInputStream(is);
            DataInputStream dis= new DataInputStream(bis);
            String msgReceived="";



            while (!msgReceived.equals("colse")){
                msgReceived=dis.readUTF();

                if(msgReceived.equalsIgnoreCase("get-cookie")){
                    String cookieValue=cookie.returnCookie();
                    System.out.println(cookieValue);
                }
            }
        }catch(IOException E){
            s.close();
            ss.close();
        }


    }



    
}
