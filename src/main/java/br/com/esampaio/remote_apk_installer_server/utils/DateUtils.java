package br.com.esampaio.remote_apk_installer_server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String toString(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
