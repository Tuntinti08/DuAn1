/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infrastructures.constant;

/**
 *
 * @author QUOC HUY
 */
public class ValidateConstant { 

    public static final String REGEX_HO_OR_TEN = "^[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãè"
            + "éêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔ"
            + "ỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ][a-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚ"
            + "ĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềể"
            + "ếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$"; // HỌ TÊN KHÔNG DẤU CÓ KHOẢNG TRẮNG
    public static final String REGEX_MAT_KHAU = ".{6,}"; // ÍT NHẤT 6 KÝ TỰ
    public static final String REGEX_PHONE_NUMBER = "(0?)(3[2-9]|5[6|8|9]|7[0|6-9]"
            + "|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}"; // LÀ SỐ BẮT ĐẦU TỪ 0
    public static final String REGEX_CMND = "\\d{12}"; // LÀ SỐ 10 KÝ TỰ
    public static final String REGEX_CHU_KHONG_CO_KHOANG_TRANG = "[a-zA-Z0-9]+";
    public static final String REGEX_USER_NAME = "[a-zA-Z0-9]+.{12,}";  // số và chữ ít nhất 12 ký tự
    public static final String REGEX_SO = "[0-9]+";
    public static final String REGEX_DATE = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]"
            + "[0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]"; // kiểu date demo: "2019-07-10 12:12:00"

}
