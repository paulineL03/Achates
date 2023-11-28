package com.example.achates1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class home extends Fragment {
    public String getCom;
    ImageView img;
    EditText input;
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    Button mic;


    public home() {
        // Required empty public constructor
        //img = (ImageView) img.findViewById(R.id.homeAchates);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View v = inflater.inflate(R.layout.fragment_home,container,false);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }


        mic = v.findViewById(R.id.mic);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        Button button=(Button)v.findViewById(R.id.gbutton);
        input=(EditText)v.findViewById(R.id.command);
        img=(ImageView)v.findViewById(R.id.homeAchates);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                input.setText("");
                input.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                input.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        OnClickListener listnr=new OnClickListener() {
            @Override
            public void onClick(View v) {
                getCom= input.getText().toString();
                commands();
            }
        };
        button.setOnClickListener(listnr);

        mic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                    input.setHint("what do you want to say?");
                    mic.setText("Hold");
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    mic.setText("Listening..");
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        return v;

    }

    public void commands(){
        switch (getCom.toLowerCase()) {
            case "hello":
            case "hi":
            case "greetings":
            case "what's up":
                img.setImageResource(R.drawable.wave);
                break;
            case "camera":
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                break;
            case "image label":
            case "imagelabeling":
            case "imagelabel":
            case "image labeling":
            case "image":
            case "classify":
            case "classify image":
                Intent i=new Intent(getActivity(), imageLabel.class);
                startActivity(i);
                break;
            case "destroy":
                img.setImageResource(R.drawable.destroy);
                break;
            case "i'm hungry":
            case "give me food":
            case "food":
            case "hungry":
            case "chicken":
            case "i want chicken":
                img.setImageResource(R.drawable.chicken);
                break;
            case "lock all doors":
            case "lock doors":
                Toast.makeText(getActivity().getApplicationContext(),"Doors locked",Toast.LENGTH_SHORT).show();
                break;
            case "unlock doors":
            case "unlock all doors":
                Toast.makeText(getActivity().getApplicationContext(),"Doors unlocked",Toast.LENGTH_SHORT).show();
                break;
            case "youtube":
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(getActivity(), "Not Installed", Toast.LENGTH_LONG).show();
                }
                break;
            case "google":
                Intent goo = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.google");
                if (goo != null) {
                    startActivity(goo);
                } else {
                    Toast.makeText(getActivity(), "Not Installed", Toast.LENGTH_LONG).show();
                }
                break;
            case "off room one":
            case "off room 1":
                Toast.makeText(getActivity(), "Room 1 lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "off room two":
            case "off room 2":
                Toast.makeText(getActivity(), "Room 2 lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "off room three":
            case "off room 3":
                Toast.makeText(getActivity(), "Room 3 lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "on room one":
            case "on room 1":
                Toast.makeText(getActivity(), "Room 1 lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "on room two":
            case "on room 2":
                Toast.makeText(getActivity(), "Room 2 lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "on room three":
            case "on room 3":
                Toast.makeText(getActivity(), "Room 3 lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "off kitchen lamp":
            case "kitchen lamp off":
                Toast.makeText(getActivity(), "Kitchen lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "on kitchen lamp":
            case "kitchen lamp on":
                Toast.makeText(getActivity(), "Kitchen lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "off outdoor lamp":
            case "outdoor lamp off":
                Toast.makeText(getActivity(), "Outdoor lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "on outdoor lamp":
            case "outdoor lamp on":
                Toast.makeText(getActivity(), "Outdoor lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "on living room":
            case "living room on":
                Toast.makeText(getActivity(), "Living room lamp turned on", Toast.LENGTH_LONG).show();
                break;
            case "off living room":
            case "living room off":
                Toast.makeText(getActivity(), "Living room lamp turned off", Toast.LENGTH_LONG).show();
                break;
            case "on stove":
            case "stove on":
                Toast.makeText(getActivity(), "Stove turned on", Toast.LENGTH_LONG).show();
                break;
            case "off stove":
            case "stove off":
                Toast.makeText(getActivity(), "Stove turned off", Toast.LENGTH_LONG).show();
                break;
            case "on tv":
            case "tv on":
                Toast.makeText(getActivity(), "TV turned on", Toast.LENGTH_LONG).show();
                break;
            case "off tv":
            case "tv off":
                Toast.makeText(getActivity(), "TV turned off", Toast.LENGTH_LONG).show();
                break;
            case "on speaker":
            case "speaker on":
                Toast.makeText(getActivity(), "Speaker turned on", Toast.LENGTH_LONG).show();
                break;
            case "off speaker":
            case "speaker off":
                Toast.makeText(getActivity(), "Speaker turned off", Toast.LENGTH_LONG).show();
                break;
            case "automate":
            case "automation":
                Intent a=new Intent(getActivity(), homeAutomation.class);
                startActivity(a);
                break;
            case "security":
            case "home security":
                Intent b=new Intent(getActivity(), homeSecu.class);
                startActivity(b);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(),"I don't know what you mean.",Toast.LENGTH_SHORT).show();
                img.setImageResource(R.drawable.confused);

        }
        /*if (getCom.equalsIgnoreCase("hello")){
            img.setImageResource(R.drawable.wave);
        }
        else if(getCom.equalsIgnoreCase("camera")){

        }*/
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

}