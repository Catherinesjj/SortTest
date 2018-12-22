import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.Test;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
public class TestUI {
	
	@Test
	public static void PlanStart(){
		String resultDate = "";
		String targetPath = "http://www.mapquestapi.com/geocoding/v1/address?key=KEY&location=Washington,DC";
		try {
			URL url = new URL(targetPath);
			//打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			PrintWriter out = null;
			//请求方式
			conn.setRequestMethod("GET");
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			out.print(resultDate);
			//缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            //关闭流
            is.close();
            conn.disconnect();
            //获取返回json数据，对json数据进行解析
            JsonParser parse =new JsonParser();  //创建json解析器
            JsonObject json=(JsonObject) parse.parse(str);  //创建jsonObject对象
            JsonObject result = json.get("results").getAsJsonArray().get(0).getAsJsonObject();
            JsonObject providedLocation = result.get("providedLocation").getAsJsonObject();
            JsonObject locations = result.get("locations").getAsJsonArray().get(0).getAsJsonObject();
            //获取解析的坐标
            System.out.println("location:"+providedLocation.get("location").getAsString());
            System.out.println("latLng:"+locations.get("latLng").getAsJsonObject());
            System.out.println("displayLatLng:"+locations.get("displayLatLng").getAsJsonObject());
            System.out.println("mapUrl:"+locations.get("mapUrl").getAsString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
	}
}
