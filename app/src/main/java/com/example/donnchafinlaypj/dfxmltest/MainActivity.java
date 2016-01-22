package com.example.donnchafinlaypj.dfxmltest;

import java.net.URI;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	XMLGettersSetters data;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		new PoisListAsyncTask(this).execute();



	}
	//===========================================================================================
	//
	//===========================================================================================
	private class PoisListAsyncTask extends AsyncTask<Void, Void, XMLGettersSetters>
	{
		Context context;
		URI uri;

		public PoisListAsyncTask(Context context) {
			this.context = context;
		}



		protected XMLGettersSetters doInBackground(Void... unused) {
			XMLGettersSetters data = null;
			try {

				/**
				 * Create a new instance of the SAX parser
				 **/
				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();


				URL url = new URL("http://www.xmlfiles.com/examples/cd_catalog.xml"); // URL of the XML

				/**
				 * Create the Handler to handle each of the XML tags.
				 **/
				XMLHandler myXMLHandler = new XMLHandler();
				xmlR.setContentHandler(myXMLHandler);
				xmlR.parse(new InputSource(url.openStream()));

			} catch (Exception e) {
				System.out.println(e);
			}

			data = XMLHandler.data;

			return data;
		}

		protected void onPostExecute(XMLGettersSetters data) {

			/**
			 * Get the view of the layout within the main layout, so that we can add TextViews.
			 **/
			View layout = findViewById(R.id.layout);

			/**
			 * Create TextView Arrays to add the retrieved data to.
			 **/
			TextView title[];

			TextView artist[];

			TextView country[];

			TextView company[];

			TextView price[];

			TextView year[];

			//

			/**
			 * Makes the TextView length the size of the TextView arrays by getting the size of the
			 **/
			title = new TextView[data.getTitle().size()];
			artist = new TextView[data.getArtist().size()];
			country = new TextView[data.getCountry().size()];
			company = new TextView[data.getCompany().size()];
			price = new TextView[data.getPrice().size()];
			year = new TextView[data.getYear().size()];


			/**
			 * Run a for loop to set All the TextViews with text until
			 * the size of the array is reached.
			 *
			 **/
			for (int i = 0; i < data.getTitle().size(); i++) {

				title[i] = new TextView(context);
				title[i].setText("Title = "+data.getTitle().get(i));

				artist[i] = new TextView(context);
				artist[i].setText("Artist = "+data.getArtist().get(i));

				country[i] = new TextView(context);
				country[i].setText("Country = "+data.getCountry().get(i));

				company[i] = new TextView(context);
				company[i].setText("Company = "+data.getCompany().get(i));

				price[i] = new TextView(context);
				price[i].setText("Price = "+data.getPrice().get(i));

				year[i] = new TextView(context);
				year[i].setText("Year = "+data.getYear().get(i));

				((ViewGroup) layout).addView(title[i]);
				((ViewGroup) layout).addView(artist[i]);
				((ViewGroup) layout).addView(country[i]);
				((ViewGroup) layout).addView(company[i]);
				((ViewGroup) layout).addView(price[i]);
				((ViewGroup) layout).addView(year[i]);
			}

			setContentView(layout);

			}

	}
}