package com.example.gabinoluna.overflow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PostResponseAsyncTask extends AsyncTask<String, Void, String> {

    private ProgressDialog progressDialog; // progress dialog to be shown when processing

    private AsyncResponse delegate; // The delegate that will send message containing the result.
    private Context context; // context to be used with progress dialog
    private HashMap<String, String> postData =
            new HashMap<String, String>(); // the data that will be sent using POST
    private String loadingMessage = "Loading..."; // the message to be shown in the progress dialog


    /* Start of constructor */
//    public PostResponseAsyncTask(AsyncResponse delegate){ // constructor without POST data
//        this.delegate = delegate;
//        this.context = (Context)delegate;
//    }

    public PostResponseAsyncTask(AsyncResponse delegate,
                                 HashMap<String, String> postData){ // constructor with POST data
        this.delegate = delegate; // this is the activity to where we will be delegating the message
        this.context = (Context)delegate; // since we passed an Activity object
        this.postData = postData; // initializing the POST data
    }

//    public PostResponseAsyncTask(AsyncResponse delegate, String loadingMessage){ // constructor with loading message
//        this.delegate = delegate;
//        this.context = (Context)delegate;
//        this.loadingMessage = loadingMessage;
//    }

//    public PostResponseAsyncTask(AsyncResponse delegate,
//                                 HashMap<String, String> postData, String loadingMessage){ // constructor with POST data and loading message
//        this.delegate = delegate;
//        this.context = (Context)delegate;
//        this.postData = postData;
//        this.loadingMessage = loadingMessage;
//    }
    /* End of constructor */

    @Override
    protected void onPreExecute() { // before the request executes, this is done in the main UI thread.
        super.onPreExecute();
        progressDialog = new ProgressDialog(context); // instantiate, set and show the progress dialog while we perform the request
        progressDialog.setMessage(loadingMessage);
        progressDialog.show();
    }//onPreExecute

    @Override
    protected String doInBackground(String... urls){ // urls are the parameters passed

        String result = ""; // this is the string that will store the result from the POST request.

        result = invokePost(urls[0], postData); // we receive the result from invokePost(String requestURL, HashMap<String, String> postDataParams)
        // where we pass the URL for POST and the hashmap for the POST data which key(s) and value(s) are strings.
        return result; // we return the result (response from the connection)
    }//doInBackground

    private String invokePost(String requestURL, HashMap<String, String> postDataParams) { // the string that invokes (call on) the POST request
        URL url;
        String response = ""; // the response from the POST request
        try {
            url = new URL(requestURL); // creating a URL variable with the url we will call for the POST request

            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // attempting the connection to the url
            conn.setReadTimeout(15000); // waits up to 15 seconds for data to start reading (incoming)
            conn.setConnectTimeout(15000); // the connection can last for UP TO 15 seconds
            conn.setRequestMethod("POST"); // setting the request method to POST (could also be GET)
            conn.setDoInput(true); // setting the URL connection for input (default true)
            conn.setDoOutput(true); // setting the URL connection for output (default false)

            OutputStream os = conn.getOutputStream(); // the output stream from the connection
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8")); // copying the output stream on the buffered writer
            writer.write(getPostDataString(postDataParams)); // we write our POST data into the output stream in the appropriate format.

            writer.flush(); // flush out the writer
            writer.close(); // close out the writer
            os.close(); // closing the output stream from the POST request
            int responseCode = conn.getResponseCode(); // get the status code for the HTTP response message

            if (responseCode == HttpsURLConnection.HTTP_OK) { // if the response code returns as OK
                String line;
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(conn.getInputStream())); // getting the input stream from the connection
                while ((line = br.readLine()) != null) { // adding each line from the input stream into the response
                    response+=line;
                }
            }
            else {  // if the response code doesn't return OK
                response="";

                Log.i("PostResponseAsyncTask", responseCode + ""); // log the code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // we return the response from the connection
    }//invokePost

    private String getPostDataString(HashMap<String, String> params) // method to get the POST data string
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()){ // for - in loop to traverse each of the item
            if (first)
                first = false; // if it's the first skip over it
            else
                result.append("&"); // if it isn't the first then we append the identifiers after each parameter

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8")); // appending the key of the value
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8")); // appending the value
        }

        return result.toString(); // return the result as a string
    }//getPostDataString

    @Override
    protected void onPostExecute(String result) {

        if(progressDialog.isShowing()){ // we dismiss the process dialog since we have completed
            // our connection to the url
            progressDialog.dismiss();
        }

        result = result.trim(); // we trim the result string (remove any leading or trailing spaces)
        delegate.processFinish(result); // we pass the result to the process finish to be sent to the callee activity
    }//onPostExecute

    //Setter and Getter
    public String getLoadingMessage() {
        return loadingMessage;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    public HashMap<String, String> getPostData() {
        return postData;
    }

    public void setPostData(HashMap<String, String> postData) {
        this.postData = postData;
    }

    public Context getContext() {
        return context;
    }

    public AsyncResponse getDelegate() {
        return delegate;
    }

    //End Setter & Getter

}
