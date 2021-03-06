package com.mehmetakiftutuncu.textinstead.activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mehmetakiftutuncu.textinstead.Constants;
import com.mehmetakiftutuncu.textinstead.utilities.Dialogs;

/**
 * Activity for showing the question dialog
 * 
 * @author Mehmet Akif Tütüncü
 */
public class DialogActivity extends Activity
{
	/** Tag for logging */
	public static final String DEBUG_TAG = "TextInstead_DialogActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Get intent extras
        Bundle extras = getIntent().getExtras();
        
        // If there are some extras
        if(extras != null)
        {
        	// Get number of the last call and name of the last call and then show question dialog
        	String number = extras.getString(Constants.EXTRA_NUMBER);
        	String name = extras.getString(Constants.EXTRA_NAME);
        	
        	// If the activity is started with an action
        	if(getIntent().getAction() != null)
        	{
        		// Activity is started through the send sms action button in reminder notification
            	if(getIntent().getAction().equals(Constants.ACTION_SEND_SMS))
            	{
            		Log.d(DEBUG_TAG, "Send sms action is clicked. Going to show messages dialog for [Name: " + name + ", Number: " + number + "]");
            		
            		new Dialogs(this, name, number).showMessagesDialog();
            		
            		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            		notificationManager.cancelAll();
            		
            		return;
            	}
            	
            	// Activity is started through the call again action button in reminder notification
            	if(getIntent().getAction().equals(Constants.ACTION_CALL_AGAIN))
            	{
            		Log.d(DEBUG_TAG, "Call aga'n action is clicked. Going to call [Name: " + name + ", Number: " + number + "]");
            		
            		new Dialogs(this, name, number).callNumber();
            		
            		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            		notificationManager.cancelAll();
            		
            		return;
            	}
        	}
        	
        	// Activity started normally. Show question dialog
        	Log.d(DEBUG_TAG, "Going to show question dialog for [Name: " + name + ", Number: " + number + "]");
        	
        	new Dialogs(this, name, number).showQuestionDialog();
        }
    }
}