package retile.tool;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TcpZhiQianCommunicationUtil {


    public static OutputStream outputStream;

    public static void oneServer(int port) throws IOException {
        //1、a)创建一个服务器端Socket，即SocketService
        //搭建服务器端
        ServerSocket server = new ServerSocket(port);//开始监听客户端的请求，并阻塞
        Socket socket=server.accept();//请求收到后，自动建立连接。通过IO流进行数据传输
        System.out.println("连接建立成功");
        InputStream is = socket.getInputStream();
        outputStream = socket.getOutputStream();
        byte[] bytes = new byte[15];
        String repeat = "";
        while(is.read(bytes) != 0) {
            System.out.println("["+System.currentTimeMillis() +"]:"+ bytes2HexString(bytes));
            //继电器状态修改后上报数据
            String str = bytes2HexString(bytes);
            if(!repeat.equals(str)){
                repeat = str;
                System.out.println(str);
            }
        }
        socket.close();
        server.close();
    }

    public static void sendServer(byte[] bytes) {
        PrintWriter printWriter = null;

        try{
            Socket socket = new Socket("192.168.11.223",1030);
            System.out.println("连接建立成功");
            printWriter = new PrintWriter(new OutputStreamWriter((socket.getOutputStream())));
            printWriter.println(bytes);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            printWriter.close();
        }
    }

    public static String bytes2HexString(byte[] b) {
        String r = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase()+" ";
        }
        return r;
    }


    public static void main(String[] args) {


        Map<String,String> a = new HashMap<>();
        a.put("a","b");
        a.put("b","b");
        a.put("c","b");

        Map<String,String> b = new HashMap<>();
        b.put("a","c");
        b.put("b","c");
        b.put("c","c");

        Map<String,String> c = new HashMap<>();
        c.put("a","c");
        c.put("b","c");
        c.put("c","c");

        Map<String,String> d = new HashMap<>();
        c.put("a","c");
        c.put("b","c");
        c.put("c","c");

        Map<String,String> e = new HashMap<>();
        c.put("a","c");
        c.put("b","c");
        c.put("c","c");


        List<Map<String,String>> ds = new ArrayList<>();
        ds.add(a);
        ds.add(c);
        ds.add(b);

        Map<String, List<Map<String,String>>> map = ds.stream().collect(Collectors.groupingBy(m->m.get("a")));
        System.out.printf("ma");
    }

}
