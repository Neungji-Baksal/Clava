package kr.co.claveteam.Clava.parts;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(){
        super("비밀번호가 틀렸습니다.");
    }
}
