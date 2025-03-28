import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";  // Mật khẩu gốc
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
    }
}