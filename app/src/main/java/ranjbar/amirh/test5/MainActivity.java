package ranjbar.amirh.test5;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    // constants used when saving/restoring state
    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentBillTotal; // bill amount entered by the user
    private int currentCustomPercent; // tip % set with the SeekBar

    private EditText tip10EditText; // displays 10% tip
    private EditText total10EditText; // displays total with 10% tip

    private EditText tip15EditText; // displays 15% tip
    private EditText total15EditText; // displays total with 15% tip

    private EditText tip20EditText; // displays 20% tip
    private EditText total20EditText;  // displays total with 20% tip

    private EditText billEditText; // accepts user input for bill total
    private TextView customTipTextView; // displays custom tip percentage

    private EditText tipCustomEditText; // displays custom tip amount
    private EditText totalCustomEditText; // displays total with custom tip


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainl);

        if(savedInstanceState==null)
        {
            currentBillTotal=0.0;
            currentCustomPercent=18;
        }
        else
        {
            currentBillTotal=savedInstanceState.getDouble(BILL_TOTAL);
            currentCustomPercent=savedInstanceState.getInt(CUSTOM_PERCENT);
        }
        tip10EditText=(EditText)findViewById(R.id.tip10edittext);
        total10EditText=(EditText)findViewById(R.id.total10dittext);
        tip15EditText = (EditText) findViewById(R.id.tip15edittext);
        total15EditText = (EditText) findViewById(R.id.total15dittext);
        tip20EditText = (EditText) findViewById(R.id.tip20edittext);
        total20EditText = (EditText) findViewById(R.id.total20dittext);

        customTipTextView=(TextView)findViewById(R.id.customtiptextview);
        customTipTextView.setText(currentCustomPercent+"%");

        tipCustomEditText=(EditText)findViewById(R.id.customtipeditText);
        totalCustomEditText=(EditText)findViewById(R.id.totalcustomeditext);

        billEditText=(EditText)findViewById(R.id.billedittext);

        billEditText.addTextChangedListener(billEditTextWatcher);

        SeekBar customSeekBar=(SeekBar)findViewById(R.id.customseekbar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
        customSeekBar.setProgress(currentCustomPercent);
    }

    private void updateStandard()
    {
        // calculate bill total with a ten percent tip
        double tenPercentTip = currentBillTotal * 0.1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;

        // set tipTenEditText's text to tenPercentTip
        tip10EditText.setText(String.format("%.02f", tenPercentTip));

        // set totalTenEditText's text to tenPercentTotal
        total10EditText.setText(String.format("%.02f", tenPercentTotal));

        // calculate bill total with a fifteen percent tip
        double fifteenPercentTip = currentBillTotal * 0.15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;

        // set tipFifteenEditText's text to fifteenPercentTip
        tip15EditText.setText(String.format("%.02f", fifteenPercentTip));

        // set totalFifteenEditText's text to fifteenPercentTotal
        total15EditText.setText(String.format("%.02f", fifteenPercentTotal));

        // calculate bill total with a twenty percent tip
        double twentyPercentTip = currentBillTotal * 0.2;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        // set tipTwentyEditText's text to twentyPercentTip
        tip20EditText.setText(String.format("%.02f", twentyPercentTip));

        // set totalTwentyEditText's text to twentyPercentTotal
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));

    }

    private void updateCustom()
    {
        // set customTipTextView's to math the seekbar position
        customTipTextView.setText(currentCustomPercent+"%");

        //Calculate the custom tip amount
        double customTipAmount = currentBillTotal * currentCustomPercent * 0.01;

        // calculate the total bill, including the custom tip
        double customTotalAmount = currentBillTotal + customTipAmount;

        // display the tip and total bill amounts
        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(BILL_TOTAL,currentBillTotal);
        outState.putInt(CUSTOM_PERCENT,currentCustomPercent);

    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            currentCustomPercent = seekBar.getProgress();
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {
              //seekBar.setProgress(currentCustomPercent);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };

    private TextWatcher billEditTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int  before, int count)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int  before, int count)
        {
            try
            {
                currentBillTotal = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                currentBillTotal = 0.0;
            }
            updateStandard();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    };
}
