package retile;


import java.net.URLEncoder;
import java.util.Scanner;


public class Search {


    //101.34.13.141 11000
//    static String baseUrl = "https://www.biqusa.org";

    static String baseUrl = "";




    static String serverIp = "http://1.15.225.62:11000";

    public static void main(String[] args) {
        try{
            search();
//            menuList("6854397012708690947");

            //        "7114257585896688671"
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void search() throws Exception {
        String params = URLEncoder.encode("夜的命名术");
        String res = HttpUtils.getHttpRequestData(serverIp+"/search","name="+params + "&page="+0);
        System.out.println(RSAUtils.decryptByPrivateKey(res));
    }

    public static void menuList(String bookId) throws Exception {
        String res = HttpUtils.getHttpRequestData(serverIp+"/list5","req="+RSAUtils.encryptByPublicKey(bookId));
        System.out.println(RSAUtils.decryptByPrivateKey(res));
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while (true){
            String text = scanner.nextLine();
            if(isNum(text) ){
                index = Integer.parseInt(text);
                printOut(index,bookId);
            }else if("exit".equals(text)){
                break;
            }else{
                index ++;
                System.out.println("index:" + index);
                printOut(index,bookId);
            }
        }


    }

    public static void printOut(int index,String bookId) throws Exception {
        String params = "size=" + index+"&bookId=" + bookId;
        String res = HttpUtils.getHttpRequestData(serverIp+"/sculling5",params);
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
