import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerSocketJava {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // сервер сокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
//        try {
//            //Запускаем сервер на определенном порту и принимаем соединение
//            try {
//                server = new ServerSocket(8080); // Порт может выбрать любой в доступном диапазоне 0-65536.
//                // Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
//                System.out.println("Сервер запущен!"); // хорошо бы серверу
//                //   объявить о своем запуске
//                clientSocket = server.accept(); //ждем подключения/прослушиваем входящий запрос
//                //метод accept() блокирует текущий поток до тех пор, пока не будет установлено соединение.
//                try { // установив связь и воссоздав сокет для общения созданию потоков ввода/вывода.можем принимать сообщения
//
//                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//                    System.out.println("New connection accepted");  //оповещаем что соединение принято
//                    final String name = in.readLine(); // ждём пока клиент что-нибудь нам напишет
//                    System.out.println(name);
//                    //выводим принятую строку на экран вместе с номером порта, с которого это соединение пришло
//                    out.write(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
//                    //покрасивее
////                    out.write("Hi %s, your port is %d" + name + "\n" + clientSocket.getPort());
//                    out.flush(); // выталкиваем все из буфера
//
//                } finally { // в любом случае сокет будет закрыт
//                    clientSocket.close();
//                    // потоки тоже хорошо бы закрыть
//                    in.close();
//                    out.close();
//                }
//            } finally {
//                System.out.println("Сервер закрыт!");
//                server.close();
//            }
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }

        try (ServerSocket server = new ServerSocket(8080);) { // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            try (Socket clientSocket = server.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

                System.out.println("New connection accepted");
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

            } catch (SocketException e) {
                // если не получилось создать сокет
                System.out.println("Socket: " + e.getMessage());
            }
        } catch (IOException e) {
            // ошибка при приеме
            System.out.println("IO: " + e.getMessage());
        }
    }
}