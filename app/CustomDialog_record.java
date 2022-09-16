package com.medisook.app;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class CustomDialog_record extends AlertDialog implements View.OnClickListener{
    private final ArrayList<DrugItem> drugItemArrayList;
    private ArrayList<String> hashtagArrayList;
    int position;
    TextView TextView_get, textValue;
    private EditText to_date;
    TextView drugName_view;
    String drugImage;
    private TextView inital_date;
    private TextView final_date;
    private EditText from_date;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView drugName;
    private TextView et_Date;
    private TextView txt;
    private TextView start;
    private TextView et_Date1;
    private Button okButton;
    private Button wish_btn;
    private Context mContext;
    private EditText et_record;
    private ImageButton good_btn;
    private ImageButton bad_btn;
    private CustomDialog_record_Listener customDialogListener;
    int count_tag;
//    public CustomDialog_record() {
//        super();
//    }
//
//    public void setArguments(Bundle args) {
//    }

    public void show(FragmentManager supportFragmentManager, String sample_dialog_fragment) {
    }

    interface CustomDialog_recordListener{
        void onOkClicked(String text);
        void onOkClicked(ArrayList<RecordItem> text);
    }

    public CustomDialog_record(Context context, int position, ArrayList<DrugItem> drugItemArrayList) {
        super(context);
        this.mContext = context;
        this.drugItemArrayList = drugItemArrayList;
        this.position = position;
    }

    interface CustomDialog_record_Listener {
        void onOkClicked(String result);
    }

    public void setDialogListener(CustomDialog_record_Listener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }
    Calendar myCalendar = Calendar.getInstance();

    class BtnOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            String GoodBad = "null";
            switch (view.getId()){
                case R.id.good_btn:
                    GoodBad = "good";
                    Log.v("기록", "좋아요 누름");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pop);

        inital_date = (TextView) findViewById(R.id.to_date);
        final_date = (TextView) findViewById(R.id.from_date);
        textView1 = (TextView) findViewById(R.id.hashtag1);
        textView2 = (TextView) findViewById(R.id.hashtag2);
        textView3 = (TextView) findViewById(R.id.hashtag3);
        drugName = (TextView) findViewById(R.id.drugName);
        drugName.setText(drugItemArrayList.get(position).getDrugName());
        drugImage = drugItemArrayList.get(position).getDrugImg();
        good_btn = (ImageButton) findViewById(R.id.good_btn);
        good_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GoodBad = "good";
                Log.v("기록", "좋아요 누름");
            }
        });
        bad_btn = (ImageButton) findViewById(R.id.bad_btn);
        bad_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GoodBad = "bad";
                Log.v("기록", "싫어요 누름");
            }
        });
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        et_Date = (TextView) findViewById(R.id.to_date);
        et_Date1 = (TextView) findViewById(R.id.from_date);
        et_record = (EditText) findViewById(R.id.record);
        hashtagArrayList = new ArrayList<>(Arrays.asList("", "", ""));
        count_tag = 0;
        et_record.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.v("키보드", "이벤트" + et_record.getText());
                    if (event.getAction() != KeyEvent.ACTION_DOWN) {
                        Log.v("세영", et_record.getText().toString());
                        hashtagArrayList.add(count_tag, String.valueOf(et_record.getText().toString()));
                        Log.v("태그", String.valueOf(hashtagArrayList));
                        switch (count_tag){
                            case 0:
                                textView1.setText(String.valueOf(hashtagArrayList.get(0)));
                                textView1.setPadding(25, 0, 25, 0);
                                break;
                            case 1:
                                textView2.setText(String.valueOf(hashtagArrayList.get(1)));
                                textView2.setPadding(25, 0, 25, 0);
                                break;
                            case 2:
                                textView3.setText(String.valueOf(hashtagArrayList.get(2)));
                                textView3.setPadding(25, 0, 25, 0);
                                break;
                        }
                        count_tag +=1;




                        Log.v("태그1", String.valueOf(hashtagArrayList.get(0)));
                        Log.v("태그2", String.valueOf(hashtagArrayList.get(1)));
                        Log.v("태그3", String.valueOf(hashtagArrayList.get(2)));

                        et_record.getText().clear();
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et_record.getWindowToken(), 0);
                        //Toast.makeText(mContext.getApplicationContext(), et_record.getText(), Toast.LENGTH_LONG).show();
                        return true;

                    }
//                    switch (keyCode) {
//                        case KeyEvent.KEYCODE_1:
//                            break;
//                        case KeyEvent.KEYCODE_2:
//                            break;
//                        case KeyEvent.KEYCODE_3:
//                            break;
//                    }
                return false;
            }
        });

        okButton = (Button) findViewById(R.id.popup_ok_btn);
        good_btn.setOnClickListener(this);
        bad_btn.setOnClickListener(this);
        okButton.setOnClickListener(this);
        et_Date.setOnClickListener(this);
        et_Date1.setOnClickListener(this);
    }

    String GoodBad = "입력되지않음";
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.good_btn:
                GoodBad = "good";
                good_btn.setBackgroundResource(R.drawable.resize_goodlined);
                bad_btn.setBackgroundResource(R.drawable.resize_bad);
                Log.v("세영", GoodBad);
                break;
            case R.id.bad_btn:
                GoodBad = "bad";
                bad_btn.setBackgroundResource(R.drawable.resize_badlined);
                good_btn.setBackgroundResource(R.drawable.resize_good);
                Log.v("세영", GoodBad);
                break;
            case R.id.to_date:
                Log.v("태그", "to-date 클릭");
                DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String inital_date = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth);
                        //Log.v("보내", inital_date);
                        Log.v("세영", inital_date);
                        updateLabel();
                    }
                };
                new DatePickerDialog(mContext, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.from_date:
                Log.v("태그", "from-date 클릭");
                DatePickerDialog.OnDateSetListener myDatePicker1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String final_date = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth);
                        Log.d("세영", final_date);
                        updateLabel1();
                    }
                };
                new DatePickerDialog(mContext, myDatePicker1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.popup_ok_btn:
                Log.v("보내자1", textView1.getText().toString());
                Log.v("세영", drugName.getText().toString());
                String name = drugName.getText().toString();
                String txt1 = textView1.getText().toString();
                String txt2 = textView2.getText().toString();
                String txt3 = textView3.getText().toString();
                String start = inital_date.getText().toString();
                String end = final_date.getText().toString();
                String favor = GoodBad;
                Toast.makeText(this.mContext, "기록하기 성공!", Toast.LENGTH_SHORT).show();
                //Log.d("보내자2", "약이름: " + name + "좋아요/싫어요" + favor + "복용시작: " + start + "복용끝 :" + end + "리뷰: " + txt1 + txt2 + txt3 );
//                customDialogListener.onOkClicked("약이름: " + name + "좋아요/싫어요" + favor + "복용시작: " + start + "복용끝 :" + end +
//                        "리뷰: " + txt1 + txt2 + txt3);
                customDialogListener.onOkClicked(name+","+favor+","+start+","+end+","+txt1+","+txt2+","+txt3);
                dismiss();
                break;
        }
    }

    private void updateLabel() {
        String myFormat = "yy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date = (TextView) findViewById(R.id.to_date);
        et_date.setText(sdf.format(myCalendar.getTime()));
//        TextView et_date1 = (TextView) findViewById(R.id.from_date);
//        et_date1.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel1() {
        String myFormat = "yy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date1 = (TextView) findViewById(R.id.from_date);
        et_date1.setText(sdf.format(myCalendar.getTime()));
    }
}
