package com.uptake.upflow;

public class Constants {

    public static final String INCOMING_WEBHOOK_URL = "https://hooks.slack.com/services/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";

    public static final String FEEDBACK_QUESTION =
            "{\n" + "    \"text\": \"Would you like to provide feedback to UpFlow Team to improve the product ?\",\n" + "    \"attachments\": [\n" + "        {\n"
                    + "            \"text\": \"Did this solution work for you ?\",\n" + "            \"fallback\": \"You are unable to send feedback\",\n"
                    + "            \"callback_id\": \"wopr_game\",\n" + "            \"color\": \"#3AA3E3\",\n" + "            \"attachment_type\": \"default\",\n" + "            \"actions\": [\n"
                    + "                {\n" + "                    \"name\": \"game\",\n" + "                    \"text\": \"Yes\",\n" + "                    \"type\": \"button\",\n"
                    + "                    \"value\": \"yes\"\n" + "                },\n" + "                {\n" + "                    \"name\": \"game\",\n"
                    + "                    \"text\": \"No\",\n" + "                    \"style\": \"danger\",\n" + "                    \"type\": \"button\",\n"
                    + "                    \"value\": \"no\"\n" + "                }\n" + "            ]\n" + "        }\n" + "    ]\n" + "}";


}
