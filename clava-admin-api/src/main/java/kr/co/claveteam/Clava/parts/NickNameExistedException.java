package kr.co.claveteam.Clava.parts;

public class NickNameExistedException extends RuntimeException{

    public NickNameExistedException(String nickname){
        super("이미 등록된 아이디 입니다.");
    }
}
