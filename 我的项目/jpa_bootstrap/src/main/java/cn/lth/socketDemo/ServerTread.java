package cn.lth.socketDemo;

import cn.lth.dto.DemoDto;

import java.io.*;
import java.net.Socket;

public class ServerTread extends Thread{

    Socket socket = null;
    public ServerTread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader inr = null;
        BufferedReader bin = null;
        PrintWriter write = null;
        OutputStream out = null;
        try {
            is = socket.getInputStream();
            inr = new InputStreamReader(is);
            bin = new BufferedReader(inr);
            String str = null;
            while ((str = bin.readLine()) != null){
                System.out.println("客户端说：" + str);
            }
            socket.shutdownInput();
            out = socket.getOutputStream();
            write = new PrintWriter(out);
            write.write(new DemoDto("name", "something", "content").toString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(out != null)out.close();
                    if (write != null) write.close();
                    if(socket != null) socket.close();
                    if(inr != null)inr.close();
                    if(is != null)is.close();
                    if(bin != null)bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
