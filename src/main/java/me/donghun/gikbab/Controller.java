package me.donghun.gikbab;

import org.springframework.web.bind.annotation.*;

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
            if (line.contains(day + "(")) { // 원하는 요일 찾음
                flag = true;
                builder.append(line);
                while ((line = br.readLine()) != null) { // 달의 마지막 날인 경우 예외처리 필요
                    if (!line.contains((day + 1) + "(")) {
                        builder.append(line);
                    } else {
                        break;
                    }
                }
            }
            if (flag) // 원하는 요일을 찾고 저장까지 끝냈으므로 반복 탈출
                break;
        }
        String naive = builder.toString();

        // 2차 가공: 아침 점심 저녁 구분하기
        int breakfast = -1, lunch = -1, dinner = -1, count = 0;
        int breakfast_eng = -1, lunch_eng = -1, dinner_eng = -1; // 영어 메뉴는 제거할 생각
        String replace = naive;
//        String replace = naive.replace("<br />", "");
        for (int i = 0; i < replace.length(); i++) {
            if (replace.charAt(i) == 'A') {
                count++;
                switch (count) {
                    case 1:
                        breakfast = i;
                        break;
                    case 2:
                        breakfast_eng = i;
                        break;
                    case 3:
                        lunch = i;
                        break;
                    case 4:
                        lunch_eng = i;
                        break;
                    case 5:
                        dinner = i;
                        break;
                    case 6:
                        dinner_eng = i;
                        break;
                }
            }
        }
        String k1 = replace.substring(0, breakfast);
        String k2 = replace.substring(breakfast, breakfast_eng);
        String k3 = replace.substring(lunch, lunch_eng);
        String k4 = replace.substring(dinner, dinner_eng);
        String str = k1 + "아침<br />" + k2 + "점심<br />" + k3 + "저녁<br />" + k4;
        String[] split = str.split("메인");
        StringBuilder builder2 = new StringBuilder();
        Arrays.stream(split).forEach(s -> builder2.append(s + "<br />"));
        return builder2.toString();
    }

    @ResponseBody
    @GetMapping("/search")
    public String search(@RequestParam String input) throws IOException {
        return input;
    }

    @GetMapping("/upload")
    public String upload(){
        return "uploadForm";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String processUpload(){
        return "not yet";
    }

}