package me.donghun.gikbab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@org.springframework.stereotype.Controller
public class Controller {

    @ResponseBody
    @GetMapping("/menu")
    public String getMenu() throws IOException {
        // Make a URL to the web page
        URL url = new URL("http://dorm.cnu.ac.kr/html/kr/sub03/sub03_0304.html");

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        StringBuilder builder = new StringBuilder();
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            if(line.contains("<table class=\"default_view diet_table\">"))
                while(!(line = br.readLine()).contains("</table>"))
                    builder.append(line);
        }
        return builder.toString();
    }

}
