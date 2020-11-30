package kr.co.claveteam.Clava.parts;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("글 없음");
    }
}
