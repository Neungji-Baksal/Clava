package kr.co.claveteam.Clava.parts;

public class CircleNotFoundException extends RuntimeException{
    public CircleNotFoundException(Long id){
        super("id를 가진 동아리가 없습니다" + id);
    }

    public CircleNotFoundException(String name){
        super("이름을 가진 동아리가 없습니다" + name);
    }
}
