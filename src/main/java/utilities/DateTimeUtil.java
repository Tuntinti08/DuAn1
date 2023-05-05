package utilities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author QUOC HUY
 */
public class DateTimeUtil {

    public static Long convertDateToTimeStampSecond() {
        // lấy thời gian hiện tại
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static void main(String[] args) {
        Date d = new Date(convertDateToTimeStampSecond());
        System.out.println("Demo covert Long to Date" + d);
        
        System.out.println("Demo covert Date to Long" + d.getTime());

    }
}
