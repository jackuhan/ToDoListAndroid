package com.example.listener;

import java.util.List;
import java.util.UUID;

import com.example.bean.EventType;
import com.example.bean.ListItem;
import com.example.db.STODOSQLiteOpenHelper;
import com.example.simpletodo.MainActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class AddButtonListener implements OnClickListener {
	
	private MainActivity mainActivity;
	private List<ListItem> listItems;
	
	public AddButtonListener(MainActivity mainActivity, List<ListItem> listItems){
		this.mainActivity = mainActivity;
		this.listItems = listItems;
	}
	
	
	@Override
	public void onClick(View v) {
		addAEvent();
	}
	
	private void addAEvent(){
		//create a AlertDialog for adding a new event
		AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);

		alert.setTitle("Add A Event");
		alert.setMessage("Type your Event Here");
		
		LinearLayout linerInDialogue = new LinearLayout(mainActivity);
		linerInDialogue.setOrientation(LinearLayout.VERTICAL);
		// Set an EditText view to get user input 
		final EditText input = new EditText(mainActivity);
		input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		linerInDialogue.addView(input);
		// Set a Spinner to  choose a type
		final Spinner typeMenu = new Spinner(mainActivity);
		setupSpinner(typeMenu);
		linerInDialogue.addView(typeMenu);
		
		alert.setView(linerInDialogue);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  // Create a ListItem and store it
		  ListItem listItem = new ListItem();
		  
		  UUID uuid = UUID.randomUUID();
		  listItem.setId(uuid.toString()); //set ID
		  
		  Editable value = input.getText();
		  listItem.setItemName(value.toString());

		  String eventType = (String) typeMenu.getSelectedItem();
		  listItem.setItemType(eventType);
		  
		  listItems.add(listItem); //store to memory
		  STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(mainActivity);
		  sTODOSQLiteOpenHelper.addListItem(listItem); //store to sqlite
		  
		  mainActivity.setListItemViewContainer(); //refresh ListItemViewContainer
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog
	}
	
	// Set the EventType enum values to Spinner menu
	private void setupSpinner(Spinner spinner){
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_spinner_item, android.R.id.text1);
		for(EventType type : EventType.values()){
			spinnerAdapter.add(type.toString());
		}
		spinner.setAdapter(spinnerAdapter);
	}

}