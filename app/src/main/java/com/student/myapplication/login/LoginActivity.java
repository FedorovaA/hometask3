package com.student.myapplication.login;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.student.myapplication.R;
import com.student.myapplication.base.BaseView;
import com.student.myapplication.drawer.MenuActivity;
import com.student.myapplication.utils.LoginController;

import java.time.LocalDate;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    EditText login;
    EditText password;
    Button signIn;

    LocalDate date;

    private LoginPresenter loginPresenter;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        if(LoginController.getInstance().getLogin()!=null) {

            loginPresenter.isInput();
        }
        else {
            login = findViewById(R.id.ed_login);
            password = findViewById(R.id.ed_password);
            signIn = findViewById(R.id.btn_signIn);


            signIn.setOnClickListener(v -> {
                loginPresenter.onLogin(getLogin(), getPassword());
            });
        }
    }

    @Override
    public void onLoginSucess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDate() {
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = LocalDate.of(year,month+1,dayOfMonth);
                loginPresenter.onUserDateSet(getLogin(),date);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.setMessage("Выберите дату рождения");
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    @Override
    public void setAlert(long days) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Важное сообщение!")
                .setMessage("Вы мoжете войти через "+days + " дней")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onMenuActivity() {
        Intent intent = new Intent(getContext(), MenuActivity.class);
        startActivity(intent);
    }

    public String getLogin() {
        return login.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
interface ILoginView extends BaseView {
    void onLoginSucess(String message);
    void onLoginError(String message);
    void setDate();
    void setAlert(long days);
    void onMenuActivity();
}
