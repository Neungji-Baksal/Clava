package kr.co.claveteam.Clava.parts;

public class NicknameNotFoundException extends RuntimeException {
    public NicknameNotFoundException(String nickname) {
        super("Could not find user " + nickname);
    }
}
