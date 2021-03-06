package com.uptake.upflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
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

    @RequestMapping(method = RequestMethod.POST, value = "/api/hello2", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello2() {
        System.out.println("Hello Hackathon GET");
        String jsonString = " json String GET";
        return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/hello3", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sayHello3(@Context HttpServletRequest httpRequest) {
        System.out.println("Hello Hackathon sending");
        Enumeration<String> parameterNames = httpRequest.getParameterNames();

        String str = "";
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            str += "\n" + s + httpRequest.getParameter(s) + "\n";
        }

        String jsonString = "Hello " + httpRequest.getParameter("user_name") + ". UpFlow can't find the answer for " + httpRequest.getParameter("text") + " in it's DB";
        try {
            String responseUrl = httpRequest.getParameter("response_url");
            URL url = new URL(responseUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\n" + "    \"text\": \" " + jsonString + "<http://www.google.com|Click here> for details!\"\n" + "}";

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

            askFeedback();
        } catch (Exception e) {
            // System.out.print("Exception : \n" + e);
        }

        // return Response.status(HttpStatus.OK.value()).entity(jsonString).build();
    }

    private void askFeedback() throws IOException {
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
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/hello4", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response sayHello4(@Context HttpServletRequest httpServletRequest) {
        System.out.println("Hello Hackathon Feedback");
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        String str = "";
        while (parameterNames.hasMoreElements()) {
            str += parameterNames.nextElement();
        }

        String jsonString = "Json 3333 : " + str;
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
