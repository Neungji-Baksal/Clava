package kr.co.claveteam.Clava.application;

public class NickNameExistedException extends RuntimeException{

    NickNameExistedException(String nickname){
        super("이미 등록된 아이디 입니다.");
    }
}
