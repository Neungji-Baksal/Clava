package kr.co.claveteam.Clava.application;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String email){
        super("이미 등록된 이메일 입니다.");
    }
}
