package retile;

import com.sun.xml.internal.ws.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.alibaba.fastjson.JSON;

public class Sculling {

    static List<String> urlList = new ArrayList<String>();

    //101.34.13.141 11000
    static String baseUrl = "https://www.biqusa.org";

    static String serverIp = "http://101.34.13.141:11000";

    public static void main(String[] args) throws Exception {
        String params = URLEncoder.encode(baseUrl + "/147_147499/");
        String res = HttpUtils.getHttpRequestData(serverIp+"/list","req="+RSAUtils.encryptByPublicKey(params));
        System.out.println(RSAUtils.decryptByPrivateKey(res));
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while (true){
           String text = scanner.nextLine();
           if(isNum(text) ){
               index = Integer.valueOf(text);
               printOut(index);
           }else if("exit".equals(text)){
               break;
           }else{
               index ++;

           }
        }
    }

    public static void printOut(int index) throws Exception {
        String params = "size=" + index;
        String res = HttpUtils.getHttpRequestData(serverIp+"/sculling",params);
//        Map<String,Object> map = JSON.parseObject(RSAUtils.decryptByPrivateKey(res),Map.class);
//        if((Integer)map.get("code") == 0){
            String text = RSAUtils.decryptByPrivateKey(res);
            while (text.length() > 70){
                System.out.println(text.substring(0,70));
                text = text.substring(70);
            }
            System.out.println(text);
//        }else{
//            System.out.println(map.get("data"));
//        }

    }

    public static boolean isNum(String text){
        try{
            Integer.valueOf(text);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
