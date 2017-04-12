package com.anthony;

import com.anthony.net.HttpRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chendong239 on 2017-03-24.
 */
//5500621
public class Main {
    public static void main(String[] args) throws IOException {
        HttpRequest request=new HttpRequest();
        String url="http://www.23us.cc/html/103/103035/%s.html";
        String nextPage="5500621";
        Scanner scanner=new Scanner(System.in);
        String flag;
        do {
            String target= String.format(url, nextPage);
            String content=request.get(target);
            String title=content.substring(content.indexOf("<title>")+"<title>".length());
            title=title.substring(0,title.indexOf("</title>"));

            String mainText=content.substring(content.indexOf("<div id=\"content\">")+"<div id=\"content\">".length()-1);
            mainText=mainText.substring(0,mainText.indexOf("</div>"));
            mainText=mainText.replaceAll("<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;","\n\n");
            System.out.println(mainText);
            System.out.println(title);
            nextPage=content.substring(content.indexOf("var nextpage =")+"var nextpage =".length());
            nextPage=nextPage.substring(0+2,nextPage.indexOf(".html"));
            System.out.println(nextPage);
            flag=scanner.nextLine();
        }while(!"quit".equals(flag));

    }

    static void parse(String line)
    {
        String tmp[]=line.split("\"");
        String code=filter("[^0-9]",tmp[0]);
        String[] datas=tmp[1].split(",");
        System.out.println(Arrays.asList(datas));
    }

    static String filter(String regex,String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("").trim();
    }
}
