package com.healthcare;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	EditText editText1, etfeet, etinches, etpounds, etkg;
	RadioButton rbMale, rbFemale;
	Button btnCalculate;
	TextView tvbmi;
	RadioGroup rgbutton;
	private double BMR;
	boolean male,female;
	Spinner spinner1;
	static final double CENTIMETER_PER_INCHE = 2.54;
	static final int INCH_PER_FOOT = 12;
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editText1 = (EditText) findViewById(R.id.editText1);
		rbMale = (RadioButton) findViewById(R.id.rbMale);
		rbFemale = (RadioButton) findViewById(R.id.rbFemale);
		etfeet = (EditText) findViewById(R.id.etfeet);
		etinches = (EditText) findViewById(R.id.etinches);
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		tvbmi = (TextView) findViewById(R.id.tvbmi);
		rgbutton =(RadioGroup)findViewById(R.id.rgbutton);
		spinner1 =(Spinner)findViewById(R.id.spinner1);
		btnCalculate.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {

		double age = 0, totalheightInCentimeter, totalweightInKg = 0, totalWeight;
		double feet = 0, inches = 0;
		String etage = editText1.getText().toString();
		try {
			age = Double.parseDouble(etage);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// converting 5 feet 7 inches in cm
		String etfeet1 = etfeet.getText().toString();
		String etinche = etinches.getText().toString();
		try {
			feet = Double.parseDouble(etfeet1);
			inches = Double.parseDouble(etinche);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalheightInCentimeter = calculateHeightInCm(feet, inches);

		
		String units_of_body = String.valueOf(spinner1.getSelectedItem());
		if(units_of_body.equalsIgnoreCase("Pounds")){
			switch(v.getId()){
			case R.id.btnCalculate:
				//Toast.makeText(getApplicationContext(), "weight in pounds", Toast.LENGTH_LONG).show();
				//
				
					 double pounds = 0;
						
						try {
							pounds = Double.parseDouble(units_of_body);
							totalweightInKg = pounds * 0.45359237;
							Log.i(TAG, "totalweightINkg is pounds::"+totalweightInKg);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						
				
			}
		}else{

			try {
				
				totalweightInKg = Double.parseDouble(units_of_body);
				Log.i(TAG, "totalweightINkg is KG::"+totalweightInKg);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		}
		
				//
				//etkg.setText("");
				
			
			
			
		
		
		// converting pound in kg
		
		
		//Log.i(TAG, "totalweightINkg is::"+totalweightInKg);
		//Log.i(TAG, "totalheightInCentimeter is::"+totalheightInCentimeter);
		// no conversion required
		// String weight = etkg.getText().toString();
		// totalWeight = Double.parseDouble(weight);
		// Taking Male and Female Input
		/*male = rbMale.getText().equals("Male");
		female = rbFemale.getText().equals("Female");
		System.out.println("male==="+male);
		System.out.println("female==="+female);*/
		
		
		int selectedSexId = rgbutton.getCheckedRadioButtonId();
		rbMale = (RadioButton) findViewById(selectedSexId);
		Log.i("RadioGroup", "@@@@ Selected SEX id is :: "+ rbMale.getText());
		System.out.println("totalweightInKg==="+totalweightInKg);
		System.out.println("totalheightInCentimeter==="+totalheightInCentimeter);
		System.out.println("age==="+age);
		// men is .73 and for women is .66.
		if (rbMale.getText().equals("Male")) {
			BMR = (10 * totalweightInKg) + (6.25 * totalheightInCentimeter)- (5 * age) + 5;
		} else {
			BMR = (10 * totalweightInKg) + (6.25 * totalheightInCentimeter)- (5 * age) - 161;
		}
		
		
		/*if (rbMale.getText().equals("Male")) {
			
		} else if (rbFemale.getText().equals("Female")) {
			BMR = (10 * totalweightInKg) + (6.25 * totalheightInCentimeter)
					- (5 * age) - 161;
		}*/

		
		tvbmi.setText("Your BMR is::" + BMR);

		}

	private double calculateHeightInCm(double feet, double inches) {
		double hei = 0;
		double totalInches = (feet * INCH_PER_FOOT) + inches;
		hei = totalInches * CENTIMETER_PER_INCHE;
		return hei;
	}

}
