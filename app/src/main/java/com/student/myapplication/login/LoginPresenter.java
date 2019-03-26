package com.student.myapplication.login;

import android.os.Bundle;
import android.util.Log;

import com.student.myapplication.base.BasePresenter;
import com.student.myapplication.utils.LoginController;

import java.time.LocalDate;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{
    private String password="QWERTY123";
    @Override
    public void onCreate(Bundle saveInstance) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLogin(String login, String password) {
        if(LoginController.getInstance().getLogin()==null) {
            if (!login.isEmpty() && !password.isEmpty() && password.equals(this.password)) {
                view.setDate();
            } else {
                view.onLoginError("Invalid data");
            }
        }
        else if(!LoginController.getInstance().isUser()) {
            long days;
            days = (long) (dateCount(LoginController.getInstance().getDate()).getYear()*365.25);
            days+= dateCount(LoginController.getInstance().getDate()).getDayOfYear()-1;
            view.setAlert(days);
        }
    }

    @Override
    public void onUserDateSet(String login,LocalDate date) {
        long days;
        LoginController.getInstance().setLogin(login);
        LoginController.getInstance().setDate(date);
        if(isDateCorrect(date)){
            LoginController.getInstance().setUser(true);
            view.onMenuActivity();
        }
        else {
            LoginController.getInstance().setUser(false);
            days = (long) (dateCount(date).getYear()*365.25);
            days+= dateCount(date).getDayOfYear()-1;
           view.setAlert(days);
        }

    }

    @Override
    public void isInput() {
        long days;
        days = (long) (dateCount(LoginController.getInstance().getDate()).getYear()*365.25);
        days+= dateCount(LoginController.getInstance().getDate()).getDayOfYear()-1;
        if (!LoginController.getInstance().isUser()){
            view.setAlert(days);
        }
        else {
            view.onMenuActivity();
        }
    }


    private LocalDate dateCount(LocalDate date){
        LocalDate today = LocalDate.now();
        LocalDate temp = today.minusYears(date.getYear());
                temp = temp.minusDays(date.getDayOfYear());
        temp = temp.plusDays(1);
        LocalDate res = LocalDate.of(18,01,01);
        res = res.minusYears(temp.getYear());
        res = res.minusDays(temp.getDayOfYear());
        return res;
    }
    private boolean isDateCorrect(LocalDate date){
        if(dateCount(date).getYear()<0){
            return true;
        }
        else{
            return false;
        }
    }
}
interface ILoginPresenter{
    void onLogin(String login, String password);
    void onUserDateSet(String login,LocalDate date);
    void isInput();
}
