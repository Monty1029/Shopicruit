/**
 * Shopify challenge for calculating the total cost of lamps and wallets on the Shopicruit store.
 */
import java.net.URL;
import java.io.*;
import org.json.*;

public class Shopicruit {
	
	public static void main(String[] args) {

		try {
			//Creates character array from JSON characters from the URL
			URL url = new URL("http://shopicruit.myshopify.com/products.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			char[] chars = new char[1];
			int len;
			while ((len=br.read(chars)) != -1) {
				buffer.append(chars);
			}
			
			//Converts the buffer into a JSONArray
			JSONArray products = new JSONObject(buffer.toString()).getJSONArray("products");

			//Holds the total cost of lamps and wallets
			double cost = 0;
			
			//Cycles through the entire list of products and if there's a lamp or a wallet, then the cost will be updated
			for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				if (product.get("product_type").equals("Lamp") || product.get("product_type").equals("Wallet")) {
					JSONArray variants = product.getJSONArray("variants");
					for (int j = 0; j < variants.length(); j++) {
						cost += variants.getJSONObject(j).getDouble("price");
					}
				}
			}
			
			//Prints out the total cost of all lamps and wallets
			System.out.println(cost);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
