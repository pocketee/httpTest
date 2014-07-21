/**
 * Created by jh on 2014-07-21.
 */
import java.io.IOException;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {
    public static void main(String args[]) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://sms.khoa.go.kr/info/tide/BUSAN/201407.htm");

        try {
            httpClient.execute(httpget, new BasicResponseHandler() {
                @Override
                public String handleResponse(HttpResponse response) throws HttpResponseException,
                        IOException {
                    // 웹페이지를 그냥 갖어오면 한글이 깨져요. 인코딩 처리를 해야해요.
                    String res = new String(super.handleResponse(response).getBytes("8859_1"), "euc-kr");


                    System.out.println(res.toString());

                    return res;
                }
            });
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
