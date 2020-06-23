package me.donghun.gikbab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {

    @ResponseBody
    @GetMapping("/menu")
    public String getMenu() throws IOException {
        Date date = new Date();
        String[] splitDate = date.toString().split(" ");
        int day = Integer.parseInt(Arrays.asList(splitDate).get(2));
        URL url = new URL("http://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        while ((line = br.readLine()) != null) {
            if(line.contains(day + "(")){ // 원하는 요일 찾음
                flag = true;
                builder.append(line);
                while((line = br.readLine()) != null) { // 달의 마지막 날인 경우 예외처리 필요
                    if(!line.contains((day+1) + "(")){
                        builder.append(line);
                    }
                    else{
                        break;
                    }
                }
            }
            if(flag) // 원하는 요일을 찾고 저장까지 끝냈으므로 반복 탈출
                break;
        }
        return builder.toString();
    }

}
