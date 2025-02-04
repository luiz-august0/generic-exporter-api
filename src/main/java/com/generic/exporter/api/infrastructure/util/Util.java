package com.generic.exporter.api.infrastructure.util;

import org.springframework.util.ObjectUtils;

import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Util {

    public static boolean isEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static <T> T nvl(Object originalInstance, T returnIfNull) {
        if (originalInstance instanceof String) {
            String a = (String) originalInstance;
            if (a.trim().isEmpty()) {
                return returnIfNull;
            }
        }
        return (T) ((originalInstance == null) ? returnIfNull : originalInstance);
    }

    public static String formatter(String value, String mask) {
        try {
            if (StringUtil.isNotNullOrEmpty(value) && StringUtil.isNotNullOrEmpty(mask)) {
                MaskFormatter maskFormatter = new MaskFormatter(mask);
                maskFormatter.setValueContainsLiteralCharacters(false);

                value = maskFormatter.valueToString(value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String formatCep(String cep) {
        try {
            if (!isEmpty(cep)) {
                MaskFormatter mf = new MaskFormatter("#####-###");
                mf.setValueContainsLiteralCharacters(false);
                cep = mf.valueToString(numbers(cep));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return cep;
    }

    public static String numbers(String str) {
        String n = nvl(str, "");
        return n.replaceAll("[^0-9]", "");
    }

    public static String removeAccents(String s) {
        if (s == null) {
            return "";
        }
        CharSequence cs = new StringBuilder(s);
        String sRet = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        String[] specialCharacters = {"\\.", ",", "-", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "°", "/", "–", "|"};

        for (String specialCharacter : specialCharacters) {
            sRet = sRet.replaceAll(specialCharacter, "");
        }
        sRet = sRet.replaceAll("&", "E");

        return sRet;
    }

    public static List<String> searchBetweenStringsToNumbers(final String text, final String start, final String end) {
        final String quoteStart = Pattern.quote(start);
        final String quoteEnd = Pattern.quote(end);

        final Pattern pattern = Pattern.compile(quoteStart + "(.*?)" + quoteEnd);
        final Matcher matcher = pattern.matcher(text);

        final List<String> successMath = new ArrayList<>();

        while (matcher.find()) {
            successMath.add(matcher.group(1));
        }

        return successMath.stream()
                .map(item -> item.replaceAll("[^a-zA-Z0-9]", ""))
                .collect(Collectors.toList());
    }

    public static boolean equals(Object val1, Object val2) {
        if (val1 == null && val2 == null) {
            return true;
        } else if (val1 != null && val2 != null) {
            if (val1.getClass().isPrimitive()) {
                return val1 == val2;
            } else {
                return val1.equals(val2);
            }
        } else {
            return false;
        }
    }

    public static Integer getInteger(Object[] row, int idx) {
        try {
            return Integer.parseInt(row[idx].toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal getBigDecimal(Object[] row, int idx) {
        try {
            return new BigDecimal(row[idx].toString());
        } catch (Exception e) {
            return null;
        }
    }

    public static String getString(Object[] row, int idx) {
        try {
            return row[idx].toString();
        } catch (Exception e) {
            return "";
        }
    }

}
