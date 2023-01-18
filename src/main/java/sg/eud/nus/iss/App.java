package sg.eud.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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


       ServerSocket ss= new ServerSocket(12345);
       Socket s= ss.accept();

       try(InputStream is= s.getInputStream()){
            BufferedInputStream bis= new BufferedInputStream(is);
            DataInputStream dis= new DataInputStream(bis);
            String msgReceived="";

            try(OutputStream os= s.getOutputStream()){
                BufferedOutputStream bos= new BufferedOutputStream(os);
                DataOutputStream dos= new DataOutputStream(bos);
                while (!msgReceived.equals("colse")){
                    msgReceived=dis.readUTF();

                    if(msgReceived.equalsIgnoreCase("get-cookie")){
                        String cookieValue=cookie.returnCookie();
                        System.out.println(cookieValue);

                        dos.writeUTF(cookieValue);
                        dos.flush();
                    }

                    if (msgReceived.equalsIgnoreCase("hello")){
                        String response="hiiiiii";

                        dos.writeUTF(response);
                        dos.flush();
                    }
                }
                dos.close();
                bos.close();
                os.close();
            }catch(EOFException e){
                e.printStackTrace();
            }
            bis.close();
            dis.close();
            is.close();
        }catch(IOException E){
            s.close();
            ss.close();
        }


    }



    
}
