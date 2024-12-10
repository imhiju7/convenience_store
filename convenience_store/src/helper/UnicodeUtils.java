/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author AD
 */
public class UnicodeUtils {
     public static String removeAccent(String str) {
        if (str == null) {
            return null;
        }
        // Chuỗi chuẩn hóa để loại bỏ dấu
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");

        // Xử lý ký tự đặc biệt "Đ" và "đ"
        result = result.replaceAll("Đ", "D").replaceAll("đ", "d");
        result = result.replaceAll("ư", "u").replaceAll("Ư", "U");
        result = result.replaceAll("ă", "a").replaceAll("Â", "A").replaceAll("ă", "a");
         result = result.replaceAll("ê", "e").replaceAll("Ê", "E");

        return result;
    }
}
