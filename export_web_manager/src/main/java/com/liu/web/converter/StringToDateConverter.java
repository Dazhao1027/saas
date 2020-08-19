package com.liu.web.converter;

import org.apache.http.client.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringToDateConverter implements Converter<String, Date> {

    private String[] asd = {"yyyy-MM-dd", "yyyy.MM.dd", "yyyy年MM月dd"};

    @Override
    public Date convert(String source) {


//        try {
//            Date date=null;
//            if (StringUtils.isEmpty(source)) {
//                return null;
//            }
//            if (source.contains("-")) {
//                date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
//            }else if (source.contains(".")) {
//                date = new SimpleDateFormat("yyyy.MM.dd").parse(source);
//            }else if (source.contains("年")) {
//                date = new SimpleDateFormat("yyyy年MM月dd").parse(source);
//            }
//
//            return date;
//        } catch (ParseException e) {
//            throw new RuntimeException("您输入的日期格式有误,请用以下格式,例如:yyyy-MM-dd,yyyy.MM.dd,yyyy年MM月dd");
//        }

        if (!StringUtils.isEmpty(source)) {
            for (String s : asd) {
                try {

                    return new SimpleDateFormat(s).parse(source);
                } catch (ParseException ignored) {
                } catch (Exception e) {
                    throw new RuntimeException("您输入的日期格式有误,请用以下格式,例如:yyyy-MM-dd,yyyy.MM.dd,yyyy年MM月dd");
                }

            }
            System.out.println("格式错误!");
            throw new RuntimeException("您输入的日期格式有误,请用以下格式,例如:yyyy-MM-dd,yyyy.MM.dd,yyyy年MM月dd");
        }
        return null;
    }
}
