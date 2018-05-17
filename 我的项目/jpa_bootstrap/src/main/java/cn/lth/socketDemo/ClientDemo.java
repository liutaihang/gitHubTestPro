package cn.lth.socketDemo;

import java.io.*;
import java.net.Socket;

public class ClientDemo {

    public static void main(String[] args) throws InterruptedException, IOException {
        int num = 0;
        Socket[] sockets = new Socket[10];
        for (int i = 0; i < 10; i++) {
            sockets[i] = new Socket("localhost", 1122);
            num = new ClientDemo().invokeServer(sockets[i], num);
            System.out.println("第" + num + "次连接！");
        }
        Thread.sleep(3000);
        for(int i = 0; i < 10; i ++){
                sockets[i].close();
        }
    }

    public int invokeServer(Socket temp, int num) {
        try {
            Socket socket = temp;
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.write("hello ge jier!!" + num);
            out.flush();
            socket.shutdownOutput();

            String info = null;
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((info = reader.readLine()) != null) {
                System.out.println("服务端返回消息：" + info);
            }
            num++;
            socket.close();
            out.close();
            in.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
}
