package com.eulersbridge.isegoria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Network {
	private static String SERVER_URL = "http://eulersbridge.com:8080/";
	private String username;
	private String password;
	
	private NewsFragment newsFragment;
	private UserSignupFragment userSignupFragment;
	
	public Network(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public NetworkResponse login() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}

	public NetworkResponse logout() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public void signup(String fullName, String email, String password, String confirmPassword, String country, String university) {
		
	}
	
	public void getGeneralInfo(final UserSignupFragment userSignupFragment) {
		this.userSignupFragment = userSignupFragment;

		Runnable r = new Runnable() {
			public void run() {
				String response = getRequest("dbInterface/api/general-info");
				try {
					JSONObject jObject = new JSONObject(response);
					JSONArray jArray = jObject.getJSONArray("countrys");
					
					for (int i=0; i<jArray.length(); i++) {
						JSONObject currentCountry = jArray.getJSONObject(i);
						String country = currentCountry.getString("countryName");
						
						CountryInfo countryInfo = new CountryInfo(country);
						userSignupFragment.addCountry(countryInfo);
						
						JSONArray institutionsArray = currentCountry.getJSONArray("institutions");
						for (int j=0; j<institutionsArray.length(); j++) {
							JSONObject currentInstitution = institutionsArray.getJSONObject(j);
							
							String institution = currentInstitution.getString("institutionName");
							countryInfo.addInstituion(institution);
						}
					}
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();		
	}
	

	public void getNewsArticles(final NewsFragment newsFragment) {
		this.newsFragment = newsFragment;

		Runnable r = new Runnable() {
			public void run() {
				String response = getRequest("dbInterface/api/newsArticles/6276");
				try {
					JSONArray jArray = new JSONArray(response);
					
					for (int i=0; i<jArray.length(); i++) {
						JSONObject currentArticle = jArray.getJSONObject(i);
						int articleId = currentArticle.getInt("articleId");
						int institutionId = currentArticle.getInt("institutionId");
						String title = currentArticle.getString("title");
						String content = currentArticle.getString("content");
						String picture = null;
						String likers = null;
						long date = currentArticle.getLong("date");
						String creatorEmail = currentArticle.getString("creatorEmail");
						String studentYear = currentArticle.getString("studentYear");
						String link = null;
						
						newsFragment.addNewsArticle(articleId, institutionId, title, content, picture, likers, date, creatorEmail, studentYear, link);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();
	}

	public NetworkResponse getNewsArticle() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public NetworkResponse getElections() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public NetworkResponse getPolls() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public NetworkResponse getUsers() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public NetworkResponse getCountrys() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}

	public NetworkResponse getEvents() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}

	public NetworkResponse getForums() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public NetworkResponse getCandidates() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}

	public NetworkResponse getTickets() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}

	public NetworkResponse getPositions() {
		NetworkResponse networkResponse = null;
		
		return networkResponse;
	}
	
	public String getRequest(String params) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet();

            URI uri = new URI(SERVER_URL + params);
            httpGet.setURI(uri);
            httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(username, password), HTTP.UTF_8, false));
            httpGet.addHeader("Accept", "application/json");
            httpGet.addHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream));

            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
                readLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
        	Log.e("Isegoria", "exception", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                	Log.e("Isegoria", "exception", e);
                }
            }
        }
        return stringBuffer.toString();
    }
}
