package solekia.demo.fastfood.model;

import java.util.List;

import solekia.demo.fastfood.repository.RegisterModel;

public class RegisterPageModel{

    //タイトルを設定するための変数
    public String title;
    //データベースの値を格納するlist
    public List<RegisterModel> list;

    //お問い合わせ
    public int qa_id;
    public String qa_first_name;
    public String qa_last_name;
    public String question;
    public String qa_mail;

    //新規会員登録
    //データ格納用変数を設定
    public int customer_id;
    public String password;
    public String first_name;
    public String last_name;
    public String tell_no;
    public String mail;
    public int login;
    public int authority;
    public String shop_name;

    //ゲッターとセッター
    //お問い合わせ
    public int getQa_id(){
        return qa_id;
    }

    public void setQa_id(int qa_id){
        this.qa_id = qa_id;
    }

    public String getQa_first_name(){
        return qa_first_name;
    }

    public void setQa_first_name(String qa_first_name){
        this.qa_first_name = qa_first_name;
    }

    public String getQa_last_name(){
        return qa_last_name;
    }

    public void setQa_last_name(String qa_last_name){
        this.qa_last_name = qa_last_name;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQa_mail(){
        return qa_mail;
    }

    public void setQa_mail(String qa_mail){
        this.qa_mail = qa_mail;
    }

    //新規会員登録
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

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getTell_no(){
        return tell_no;
    }

    public void setTell_no(String tell_no){
        this.tell_no = tell_no;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
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

    public String getShop_name(){
        return shop_name;
    }

    public void setShop_name(String shop_name){
        this.shop_name = shop_name;
    }

}
