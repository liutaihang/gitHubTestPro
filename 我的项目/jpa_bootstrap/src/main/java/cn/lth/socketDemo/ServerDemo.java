package cn.lth.socketDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    private ServerSocket serverSocket;
    public ServerDemo() throws IOException {
        serverSocket = new ServerSocket(1122, 3);
        System.out.println("…………………………server start …………………………");
    }

    public static void main(String[] args) throws Exception {
        ServerDemo serverDemo = new ServerDemo();
        serverDemo.service();
    }

    public void service() throws IOException {
        Socket socket = null;
        try {
            while (true){
                socket = serverSocket.accept();
                ServerTread serverTread = new ServerTread(socket);
                serverTread.start();
                System.out.println(socket.getInetAddress() + "：" + socket.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket != null){
                socket.close();
            }
        }
    }
}
