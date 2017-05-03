package com.uptake.upflow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.POST, value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello() {
        System.out.println("Hello Hackathon");
        String jsonString = " json String ";
        return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello2() {
        System.out.println("Hello Hackathon GET");
        String jsonString = " json String GET";
        return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/hello2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello3() {
        System.out.println("Hello Hackathon sending");
        String jsonString = " json String sending";
        try {
            URL url = new URL(Constants.INCOMING_WEBHOOK_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\n" + "    \"text\": \"<http://localhost:8080/swagger-ui.html|Click here> for details!\"\n" + "}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            //            System.out.print("Exception : \n" + e);
        }

        return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/hello3", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello4() {
        System.out.println("Hello Hackathon Feedback");
        String jsonString = " json String feedback";
        try {
            URL url = new URL(Constants.INCOMING_WEBHOOK_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = Constants.FEEDBACK_QUESTION;

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            //            System.out.print("Exception : \n" + e);
        }

        return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }


}