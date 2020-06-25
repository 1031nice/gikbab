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
    @GetMapping("/todayDiet")
    public String todayDiet() throws IOException {

        // 오늘 날짜 계산
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

        // 1차 가공: 원하는 요일의 식사 정보 가져오기
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
        String naive = builder.toString();

        // 2차 가공: 아침 점심 저녁 구분하기
        int breakfast = -1, lunch = -1, dinner = -1, count = 0;
        String replace = naive.replace("<br />", "");
        for(int i=0; i<replace.length(); i++) {
            if(replace.charAt(i) == 'A'){
                count++;
                switch (count) {
                    case 1:
                        breakfast = i;
                        break;
                    case 3:
                        lunch = i;
                        break;
                    case 5:
                        dinner = i;
                        break;
                }
            }
        }
        String s1 = replace.substring(0, breakfast);
        String s2 = replace.substring(breakfast, lunch);
        String s3 = replace.substring(lunch, dinner);
        String s4 = replace.substring(dinner, replace.length());
        String str = s1 + "아침<br />" + s2 + "점심<br />" + s3 + "저녁<br />" + s4;
        String[] split = str.split("메인");
        StringBuilder builder2 = new StringBuilder();
        Arrays.stream(split).forEach(s -> builder2.append(s+"<br />"));
        return builder2.toString();
    }

}
