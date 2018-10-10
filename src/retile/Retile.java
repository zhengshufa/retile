package retile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description Created by jwing on 2018/2/5.
 */
public class Retile {

    public static void main(String[] args) {
        IChapterInterImpl ChapterInterImpl = new IChapterInterImpl();
        List<Chapter> chapterList = ChapterInterImpl.getChapter("http://www.bxwx3.org/txt/63933/");
        while (true) {
            System.out.println(chapterList.size());
            Scanner scanner = new Scanner(System.in);
            Chapter chapter;
            int index;
            try {
                index = Integer.valueOf(scanner.nextLine());
                chapter = chapterList.get(index);
                System.out.println(chapter.getTitle());
            } catch (Exception e) {
                System.out.println("emmmm");
                continue;
            }
            String url = chapter.getUrl();
            System.out.println(url);
            Get_Url(url);
            System.out.println(index);
        }
    }

    public static void Get_Url(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    //.data("query", "Java")
                    //.userAgent("头部")
                    //.cookie("auth", "token")
                    //.timeout(3000)
                    //.post()
                    .get();
            //得到html的所有东西

            Node node = doc.childNode(1);
            node = node.childNode(2);
            node = node.childNode(1);
            node = node.childNode(11);
            node = node.childNode(1);
            node = node.childNode(7);
            //分离出html下<a>...</a>之间的所有东西
            List<Node> links = node.childNodes();
            // class等于masthead的div标签
            for (Node link : links) {
                //得到<a>...</a>里面的网址
                String value = link.toString();
                value = value.replaceAll("<br>","\n");
                value = value.replaceAll("&nbsp;"," ");
                if("".equals(value.trim())) {
                    continue;
                }
                if(value.length()>90){
                    System.out.println(value.substring(0,90));
                    System.out.println(value.substring(90));
                }else{
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
