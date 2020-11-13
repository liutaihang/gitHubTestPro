package cn.lth;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(81);

        System.out.println("server start");
        Socket socket = serverSocket.accept();


        BufferedReader client = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "UTF-8"));

        PrintWriter server = new PrintWriter(socket.getOutputStream());

        String s = client.readLine();
        server.write("ServerRecive:【 " + s +" 】\r\n ServerResp: received" );
        BufferedReader sys = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

        System.out.println("clientSendMsg:" + s);
        String line = sys.readLine();
        while (StringUtils.isNotEmpty(line)
                && !line.contains("bye")){
            server.print(line);
            server.flush();

            //在系统标准输出上打印读入的字符串
            System.out.println("Server:"+line);
            //从Client读入一字符串，并打印到标准输出上
            System.out.println("Client:"+client.readLine());
            //从系统标准输入读入一字符串
            line=sys.readLine();
        }
        serverSocket.close();
        server.close();
        client.close();
        sys.close();
    }
}
