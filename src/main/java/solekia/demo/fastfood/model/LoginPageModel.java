package solekia.demo.fastfood.model;

import java.util.*;
import solekia.demo.fastfood.repository.LoginModel; 

public class LoginPageModel {
    public String title;
    public String hold_id;


    public int login;
    public String message;
    public List<LoginModel> list;
    public int authority;
    public int customer_id;
    public String password;

    public int getCustomer_id(){
        return customer_id;
    }

    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getLogin(){
        return login;
    }

    public void setLogin(int login){
        this.login = login;
    }

    public int getAuthority(){
        return authority;
    }

    public void setAuthority(int authority){
        this.authority = authority;
    }
}
