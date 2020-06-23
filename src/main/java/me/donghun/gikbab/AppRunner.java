package me.donghun.gikbab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    WeekDiet weekDiet;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URL url = new URL("http://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if(line.contains("<table class=\"default_view diet_table\">"))
                while(!(line = br.readLine()).contains("</table>"))
                    builder.append(line);
        }
        System.out.println(builder.toString());
        // 전체 식단
    }
}
