package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import Server.clientHandler;

public class HTTPServer
{
public static void connect (int port)  throws Exception
{
ServerSocket server = new ServerSocket(port);
System.out.println("--- Ready for accepting connections ---- ");
    while (true)
    {
        try
        {

            final Socket  clientSocket =  server.accept();
            Runnable clienthandler = new clientHandler(clientSocket);
              Thread thread = new Thread(clienthandler);
              thread.start();
              thread.interrupt();





//            InputStreamReader inputStream  = new InputStreamReader(clientSocket.getInputStream());
//
//            // getting input stream for this specific client connection using .getInputStream() and passing it to stream Reader
//
//            BufferedReader Buffer = new BufferedReader(inputStream);
//
//            String line = Buffer.readLine();
//
//            // storing the first line into a string
//            // since this line contains useful information such as
//            // method and path requested
//
//            System.out.println(line);
//            if(line!=null) {
//                String requiredInfo[] = line.split(" ");
//                System.out.println("Method : " + requiredInfo[0]);
//                System.out.println("Method : " + requiredInfo[1].length());
//                if (!Objects.equals(requiredInfo[1], "/")) {
//                    System.out.println("PATH : " + requiredInfo[1]);
//                    // we need to call the send file function on the specific Socket
//                    sendFile(requiredInfo[1], clientSocket);
//                }
//                else {
//                    System.out.println("response sent for invalid path");
//                    clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n " + "Please Enter a valid path to files").getBytes("UTF-8"));
//                }
//            }
//            else
//            {
//                System.out.println("response sent for empty path");
//                clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n " + "Please Enter a valid path to files").getBytes("UTF-8"));
//            }
        }
        catch (IOException ex){

            System.out.println("Can not accept new connection from client");
        }
    }
}


// sends the file to the client socket
//    public static void sendFile(String filePath, Socket clientSocket) throws Exception
//    {
//
//        // making a new output stream for the sending file
//
//        filePath = "src" + filePath;
//
//        File targetFile = new File(filePath);
//
//        if (targetFile.exists()) {
//
//               DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
//
//               byte[] fileArray = new byte[(int) targetFile.length()];
//
//               // targeting the output stream of current socket and writing the response to it
//
//               // first we need to send the http headers to the client
//
//               outputStream.writeBytes("HTTP/1.0 200 OK\r\n");
//               outputStream.writeBytes("Content-Type:  text/html; charset=utf-8\r\n");
//               outputStream.writeBytes("\r\n");
//
//               // a temporary input stream that helps in sending the file
//
//                InputStream input = new FileInputStream(filePath);
//
//                int count = input.read(fileArray);
//                while (count > 0) {
//                outputStream.write(fileArray,0,count);
//                count = input.read(fileArray);
//                }
//
//                outputStream.flush();
//           }
//
//        else{
//           clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n "+" Sorry the required file is not found ").getBytes("UTF-8"));
//       }
//
//
//
//
//
//
//
//
//
//
//    }

}








