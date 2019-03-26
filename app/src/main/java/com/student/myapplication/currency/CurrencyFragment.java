package com.student.myapplication.currency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.student.myapplication.R;

public class CurrencyFragment extends Fragment {
     private static final String ARG_СUR = "CURRENCY";

    private String mParam1;
    private EditText currency;
    private Button convert;
    private TextView txtName;
    private EditText rubleKurs;
    private Button convert2;

    private OnFragmentInteractionListener mListener;

    public CurrencyFragment() {
    }

    public static CurrencyFragment newInstance(String param1) {
        CurrencyFragment fragment = new CurrencyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_СUR, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_СUR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_currency, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        currency = view.findViewById(R.id.ed_curr);
        convert = view.findViewById(R.id.btn_convert);
        txtName = view.findViewById(R.id.txt_name);
        txtName.setText(mParam1);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currency.getText().length() != 0) {
                    double res = Double.valueOf(currency.getText().toString());
                    switch (mParam1){
                        case "SHEKEL":
                            res*=18.2;
                            break;
                        case "EURO":
                            res*=73.01;
                            break;
                        case "TENGE":
                            res*=0.17;
                            break;
                    }
                    mListener.onInputCurrencySent(String.valueOf(Math.round(res*100)/100d),mParam1);
                }
                else {
                    mListener.onMessage("Поле курс пусто");
                }
            }
        });
        rubleKurs = view.findViewById(R.id.ed_rub);
        convert2 = view.findViewById(R.id.btn_convert2);
        convert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rubleKurs.getText().length() != 0) {
                    double res = Double.valueOf(rubleKurs.getText().toString());
                    switch (mParam1){
                        case "SHEKEL":
                            res*=0.055;
                            break;
                        case "EURO":
                            res*=0.014;
                            break;
                        case "TENGE":
                            res*=5.8;
                            break;
                    }
                    mListener.onInputRubleSent(String.valueOf(Math.round(res*100)/100d),mParam1);
                }
                else {
                    mListener.onMessage("Поле курс пусто");
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void updateRubleText(String newText){
        rubleKurs.setText(newText);
    }
    public void updateCurrencyText(String newText){
        currency.setText(newText);
    }

    public interface OnFragmentInteractionListener {
        void onInputRubleSent(String input, String param);
        void onMessage(String message);
        void onInputCurrencySent(String input, String param);

    }
}
