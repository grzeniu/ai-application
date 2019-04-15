package pl.edu.wat.ai.app.integration.echangerate;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import pl.edu.wat.ai.app.currency.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
class RestClient {
    private static final String REST_SERVICE_URI = "https://api.exchangeratesapi.io/latest?symbols=%s,%s";

    static Currency fetchRate(Currency currency) {
        try {
            JSONObject json = readJsonFromUrl(String.format(REST_SERVICE_URI, currency.getFromCurrency(), currency.getToCurrency()));
            log.info(json.toString());
            Double from = (Double) json.getJSONObject("rates").get(currency.getFromCurrency());
            Double to = (Double) json.getJSONObject("rates").get(currency.getToCurrency());
            currency.updateRate(to / from);
        } catch (IOException | JSONException e) {
            log.error(e.getMessage());
        }
        return currency;
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
