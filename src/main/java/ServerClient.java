import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClient {
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static BufferedReader reader; //ридер читающий с консоли - чтобы узнать, что хочет клиент
    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 8080 (такой же, как у сервера)
                clientSocket = new Socket("localhost", 8080); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать сообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Enter what you want to say:");
                // если соединение произошло и потоки успешно созданы - мы можем
                // работать дальше и предложить клиенту, что-то ввести, если нет - вылетит ошибка
                String word = reader.readLine(); // ждём пока клиент что-нибудь напишет
                out.write(word + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
                clientSocket.close();
                in.close();
                out.close();
//            } finally { // в любом случае необходимо закрыть сокет и потоки
//                System.out.println("The client was closed...");
//                clientSocket.close();
//                in.close();
//                out.close();
//            }
        } catch (IOException e) {
            System.err.println(e);
        }
    } //main
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }}

