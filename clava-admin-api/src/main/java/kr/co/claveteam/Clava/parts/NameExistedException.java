package kr.co.claveteam.Clava.parts;

public class NameExistedException extends RuntimeException{
    public NameExistedException(String name) {
        super("이미 등록된 이름 입니다.");
    }
}
