package kr.co.claveteam.Clava.parts;

public class EmailExistedException extends RuntimeException{

    public EmailExistedException(String email){
        super("이미 등록된 이메일 입니다.");
    }
}
