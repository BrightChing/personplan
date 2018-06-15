package io.github.brightqin.personplan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author brightqin
 */
public class DataCheckUtil {

    public static Date convertDate(String dateString) throws BaseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
            throw new BaseException("时间格式错误\n应为型如 2018-09-09");
        }
        if (!dateString.equals(df.format(date))) {
            throw new BaseException("时间不合法");
        }
        return date;
    }
    ///dsf
//	public Date Datebool(String deadline,String kk) throws BaseException {
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date date;
//		try {
//			date = df.parse(deadline);
//		} catch (Exception e) {
//			throw new BaseException(kk+"时间格式错误\n 应为 型如 2016-09-09");
//		}
//		if (!deadline.equals(df.format(date))) {
//			throw new BaseException(kk+"时间不合法");
//		}
//		return date;
//	}
}