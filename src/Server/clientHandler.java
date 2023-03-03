package Server;
import java.io.*;
import java.net.Socket;
import java.util.Objects;


public class clientHandler implements Runnable
//        extends Thread
{

    public clientHandler (Socket client)
    {
        // getting a socket whose connection should have been accepted by the sever
        this.clientSocket = client;
    }

    private final Socket clientSocket;

    public void run() // overriding this method and defining all the logic here
    {
        try {
            execute();
        }
        catch (Exception ex)
        {
            System.out.println(ex);

            System.out.println("Error while handling the thread");
        }
    }

    public void execute(){


        try {

            InputStreamReader inputStream = new InputStreamReader(this.clientSocket.getInputStream());

            // getting input stream for this specific client connection using .getInputStream() and passing it to stream Reader

            BufferedReader Buffer = new BufferedReader(inputStream);

            String line = Buffer.readLine();

            // storing the first line into a string
            // since this line contains useful information such as
            // method and path requested

            System.out.println(line);
            if (line != null) {
                String requiredInfo[] = line.split(" ");
                System.out.println("Method : " + requiredInfo[0]);
                System.out.println("Method : " + requiredInfo[1].length());
                if (!Objects.equals(requiredInfo[1], "/")) {
                    System.out.println("PATH : " + requiredInfo[1]);
                    // we need to call the send file function on the specific Socket
                    sendFile(requiredInfo[1], this.clientSocket);
                } else {
                    System.out.println("response sent for invalid path");
                   this.clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n " + "Please Enter a valid path to files").getBytes("UTF-8"));
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            System.out.println("Error handling Socket from client");
        }
    }
    public void sendFile(String filePath, Socket clientSocket) throws Exception
    {

        // making a new output stream for the sending file

        filePath = "src" + filePath;

        File targetFile = new File(filePath);

        if (targetFile.exists()) {


            // creating a DataOuput Stream for sending the data

            DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

            // converting target file into array
            byte[] fileArray = new byte[(int) targetFile.length()];


            // first we need to send the http headers to the client

            outputStream.writeBytes("HTTP/1.0 200 OK\r\n");
            outputStream.writeBytes("Content-Type:  text/html; charset=utf-8\r\n");
            outputStream.writeBytes("\r\n");

            // a temporary input stream that helps in sending the file

            InputStream input = new FileInputStream(filePath);

            int count = input.read(fileArray);
            while (count > 0) {
                outputStream.write(fileArray,0,count);
                count = input.read(fileArray);
            }

            outputStream.flush();
        }

        else{
            this.clientSocket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n "+" Sorry the required file is not found ").getBytes("UTF-8"));
        }

    }
}
