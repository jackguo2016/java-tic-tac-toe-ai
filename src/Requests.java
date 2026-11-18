import java.io.*;
import java.net.URI;
import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.HashMap;


public class Requests {
    private final URI endpoint;
    private final String teamId;
    private final Map<String, String> properties = new HashMap<>();

    public Requests() {
        endpoint = URI.create(requiredEnvironment("TIC_TAC_TOE_API_URL"));
        if (!"https".equalsIgnoreCase(endpoint.getScheme()) || endpoint.getHost() == null
                || endpoint.getRawUserInfo() != null || endpoint.getRawQuery() != null
                || endpoint.getRawFragment() != null) {
            throw new IllegalArgumentException("TIC_TAC_TOE_API_URL must be an HTTPS endpoint without user info, query, or fragment.");
        }
        properties.put("x-api-key", requiredEnvironment("TIC_TAC_TOE_API_KEY"));
        properties.put("userid", requiredEnvironment("TIC_TAC_TOE_USER_ID"));
        properties.put("Content-Type", "application/x-www-form-urlencoded");
        teamId = requiredEnvironment("TIC_TAC_TOE_TEAM_ID");
        if (!teamId.matches("[1-9][0-9]*")) {
            throw new IllegalArgumentException("TIC_TAC_TOE_TEAM_ID must be a positive integer.");
        }
    }

    String teamId() {
        return teamId;
    }

    private static String requiredEnvironment(String name) {
        String value = System.getenv(name);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Set " + name + " for online play, or run Main --demo.");
        }
        return value.trim();
    }

    /**
     * Send request with GET
     *
     * @param params
     * @return
     */
    public String get(HashMap<String, String> params) throws IOException {
        StringBuilder result = new StringBuilder();

        // Add parameters
        String concatUrl = endpoint + "?";
        StringBuilder paramsSB = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsSB.append("&");
            paramsSB.append(entry.getKey());
            paramsSB.append("=");
            paramsSB.append(entry.getValue());
        }
        concatUrl += paramsSB.toString().substring(1);

        // Create connection
        HttpsURLConnection connection = (HttpsURLConnection) URI.create(concatUrl).toURL().openConnection();
        connection.setInstanceFollowRedirects(false);
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        connection.connect();

        // Read result
        try {
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {
                String temp = bufferIn.readLine();
                if (temp == null) {
                    break;
                } else {
                    result.append(temp);
                }
            }
        } catch (Exception e) {

        }

        return result.toString();
    }

    /**
     * Send request with POST
     *
     * @param params
     * @return
     */
    public String post(HashMap<String, String> params) throws IOException {
        StringBuilder result = new StringBuilder();

        // Create connection
        HttpsURLConnection connection = (HttpsURLConnection) endpoint.toURL().openConnection();
        connection.setInstanceFollowRedirects(false);
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        connection.setDoInput(true);
        connection.setDoOutput(true);

        // Add parameters
        StringBuilder paramsSB = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsSB.append("&");
            paramsSB.append(entry.getKey());
            paramsSB.append("=");
            paramsSB.append(entry.getValue());
        }
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(paramsSB.toString().substring(1));
        bufferedWriter.flush();

        // Read result
        try {
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (true) {
                String temp = bufferIn.readLine();
                if (temp == null) {
                    break;
                } else {
                    result.append(temp);
                }
            }
        } catch (Exception e) {

        }

        return result.toString();
    }
}