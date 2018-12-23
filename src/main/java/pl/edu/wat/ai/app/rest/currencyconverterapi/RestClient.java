package pl.edu.wat.ai.app.rest.currencyconverterapi;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import pl.edu.wat.ai.app.currency.Currency;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Slf4j
class RestClient {
    private static final String REST_SERVICE_URI = "http://free.currencyconverterapi.com/api/v5/convert?q=%s_%s&compact=y";

    static Currency fetchRate(Currency currency) {
        try {
            JSONObject json = readJsonFromUrl(String.format(REST_SERVICE_URI, currency.getFromCurrency(), currency.getToCurrency()));
            log.info(json.toString());
            currency.updateRate((Double) json.getJSONObject(currency.getFromCurrency() + "_" + currency.getToCurrency()).get("val"));
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
