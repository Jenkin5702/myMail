import com.tju.myMailServer.communication.DownloadServer;
import com.tju.myMailServer.communication.UploadServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileClient {

    public static void main(String[] args) throws IOException {
        UploadServer uploadServer=new UploadServer(5432);
        uploadServer.start();
        DownloadServer downloadServer=new DownloadServer(5433);
        downloadServer.start();
        upload("Figure_1.png");
        download("Figure_1.png");
    }

    public static void upload(String filePath) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5432);
        OutputStream socketOutputStream = socket.getOutputStream();
        InputStream fileInputStream = new FileInputStream(new File("to_upload", filePath));

        socketOutputStream.write(filePath.getBytes(StandardCharsets.UTF_8));

        byte[] b = new byte[1024];
        int ava = fileInputStream.available();
        while (ava > 0) {
            int i = fileInputStream.read(b);
            socketOutputStream.write(b);
            ava = fileInputStream.available();
        }
        fileInputStream.close();
        socketOutputStream.close();
        socket.close();
    }

    public static void download(String filePath) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5433);
        InputStream in=socket.getInputStream();
        OutputStream out=new FileOutputStream(new File("downloaded", filePath));
        byte[] b=new byte[1024];
        out.write(filePath.getBytes(StandardCharsets.UTF_8));
        int i=in.read(b);
        while (i>0) {
            out.write(b);
            out.flush();
            i=in.read(b);
        }
        out.close();
        in.close();
        socket.close();
    }
}