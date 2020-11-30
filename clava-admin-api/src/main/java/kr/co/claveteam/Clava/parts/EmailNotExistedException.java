package kr.co.claveteam.Clava.parts;

public class EmailNotExistedException extends RuntimeException{
    public EmailNotExistedException(String email){
        super("등록된 이메일이 아닙니다.");
    }
}
