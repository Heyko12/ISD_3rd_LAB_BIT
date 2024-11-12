/* Простой консьюмер. Слушает порт 8097, пуляет содержимое сокета в файл, оттуда его достает
 * и джейсонит, полученную строку отправляет в БД
 */

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException,
                NoSuchMethodException, InstantiationException, IllegalAccessException {
        int port = 8097;
        String filePath = "file.txt";
        System.out.println("Consumer started");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(true) {
                Socket socket = serverSocket.accept();
                receiveFile(socket, filePath);

                MiniClass obj = null;

                FileInputStream file = new FileInputStream(filePath);
                ObjectInputStream in = new ObjectInputStream(file);

                obj = (MiniClass) in.readObject();

                in.close();
                file.close();
                deleteFile(filePath);

                String jsoned = obj.getJsoned();

                sendToDB(jsoned);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static void deleteFile(String filePath) {
        File fileToDelete = new File(filePath);
 
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
 
    private static void receiveFile(Socket socket, String filePath) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
 
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
 
            while ((bytesRead = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
 
    private static void sendToDB(String jsoned) {
        try (FileWriter writer = new FileWriter("db.txt", true)) {
            writer.write(jsoned);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}