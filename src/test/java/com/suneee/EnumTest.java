package com.suneee;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.suneee.constant.LogisticalEnum;
import com.suneee.hander.CalcFactory;
import com.suneee.model.IndexField;
import com.suneee.model.User;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Digits;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EnumTest {
    public static void main(String[] args) throws Exception {

        Map<Integer, Field> map = new HashMap<>();
        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field field : declaredFields
        ) {
            if (field.isAnnotationPresent(IndexField.class)) {
                IndexField indexField = field.getAnnotation(IndexField.class);
                map.put(indexField.index(), field);
            }
        }
        LogisticalEnum name = LogisticalEnum.YTO;
        double weight = 25.1;
        CalcFactory calcFactory = new CalcFactory();
        final double v = calcFactory.create(name, weight);
        //System.out.println(" V:"+LogisticalEnum.JD);

        File file = new File("F:\\USER.xls");
        InputStream is = new FileInputStream(file);
        Workbook workbook = new HSSFWorkbook(is);
        final Sheet sheetAt = workbook.getSheetAt(0);
        for (int i = 0; i < sheetAt.getLastRowNum(); i++) {
            Row row = sheetAt.getRow(i+1);
            Object instance = null;
            try {
                instance = User.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int j = 0; j <row.getRowNum() ; j++) {
                Field  field = map.get(j);
                field.setAccessible(true);
                field.set(instance,getCellValue(row.getCell(j),field));
            }

            System.out.println(instance);
        }






    }


    public static double calcMoney(LogisticalEnum name, double weight) {
        //总运费=基本运费+超重部分
        if (name == (LogisticalEnum.JD)) {
            return 10 + weight * 1.1;
        } else if (name == (LogisticalEnum.STO)) {
            return 8 + weight * 1.3;
        } else if (name == (LogisticalEnum.YTO)) {
            return 7 + weight * 1.5;
        } else if (name == (LogisticalEnum.ZTO)) {
            return 9 + weight * 1.7;
        }
        return 0.0;
    }


    public static double calcMoney1(LogisticalEnum name, double weight) {
        //总运费=基本运费+超重部分
        switch (name) {
            case YTO:
                return 7 + weight * 1.5;
            case JD:
                return 10 + weight * 1.1;
            case ZTO:
                return 9 + weight * 1.7;
            case STO:
                return 8 + weight * 1.3;
            default:
                throw new IllegalArgumentException("NO SUCH HANDER");
        }

    }


    @SuppressWarnings("deprecation")
    private static Object getCellValue(Cell cell, Field field) throws InvalidFormatException, NotImplementedException {
        Class<?> clazz = field.getType();
        try {
            if (clazz.equals(Long.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value.longValue();
            } else if (clazz.equals(long.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0L : value.longValue();
            } else if (clazz.equals(Integer.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value.intValue();
            } else if (clazz.equals(int.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0 : value.intValue();
            } else if (clazz.equals(Float.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value.floatValue();
            } else if (clazz.equals(float.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0 : value.floatValue();
            } else if (clazz.equals(Double.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value;
            } else if (clazz.equals(double.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0 : value;
            } else if (clazz.equals(Short.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value.shortValue();
            } else if (clazz.equals(short.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0 : value.shortValue();
            } else if (clazz.equals(Byte.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? null : value.byteValue();
            } else if (clazz.equals(byte.class)) {
                Double value = getNumericCellValue(cell);
                return value == null ? 0 : value.byteValue();
            } else if (clazz.equals(BigDecimal.class)) {
                return getBigDecimalCellValue(cell, field);
            } else if (clazz.equals(String.class)) {
                String value;
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                } else {
                    HSSFDataFormatter hssfDataFormatter = new HSSFDataFormatter(Locale.getDefault());
                    value = hssfDataFormatter.formatCellValue(cell);
                }
                return StringUtils.isEmpty(value) ? null : value;
            } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
                try {
                    return cell.getBooleanCellValue();
                } catch (IllegalStateException e) {

                }
            } else if (clazz.equals(Date.class) || clazz.equals(Timestamp.class) || clazz.equals(ZonedDateTime.class) || clazz.equals(LocalDateTime.class)) {


                String value = cell.getStringCellValue();
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                try {
                    Date date = null;
                    if (date == null) {
                        return null;
                    }
                    if (clazz.equals(Date.class)) {
                        return date;
                    } else if (clazz.equals(Timestamp.class)) {
                        return new Timestamp(date.getTime());
                    } else if (clazz.equals(ZonedDateTime.class)) {
                        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    } /*else if (clazz.equals(LocalDateTime.class)) {
                        return DateTimeUtils.dateToLocalDateTime(date);
                    }*/ else {
                        return null;
                    }
                } catch (Exception e) {
                    throw new InvalidFormatException(e.getMessage(), e);
                }

            } else if (IEnum.class.isAssignableFrom(clazz)) {
                String cellValue = cell.getStringCellValue();
                for (Object value : clazz.getEnumConstants()) {
                    String description = ((IEnum) value).getValue().toString();
                    if (description.equals(cellValue)) {
                        return value;
                    }
                }
                return null;
            } else {
                //logger.error("Not support field type: " + clazz.getName());
                throw new NotImplementedException("Not support field type: " + clazz.getName());
            }
        } catch (IllegalStateException e) {
            //转换失败错误
            // logger.error(e.getMessage(), e);
            throw new InvalidFormatException(e.getMessage(), e);
        }
        return  null;
    }




    private static Double getNumericCellValue(Cell cell) {
        try {
            if (cell.getCellTypeEnum() == CellType.BLANK) {
                return null;
            } else {
                return cell.getNumericCellValue();
            }
        } catch (IllegalStateException e) {
            //尝试以文本方式读取：
            String value = cell.getStringCellValue();
            try {
                return Double.valueOf(value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    private static BigDecimal getBigDecimalCellValue(Cell cell, Field field) {
        try {
            Double value = getNumericCellValue(cell);
            if (value == null) {
                return null;
            } else {
                if (field.isAnnotationPresent(Digits.class)) {
                    Digits digits = field.getAnnotation(Digits.class);
                    if (digits.integer() != 0 || digits.fraction() != 0) {
                        return new BigDecimal(value).setScale(digits.fraction(), RoundingMode.HALF_UP);
                    } else {
                        return new BigDecimal(cell.getNumericCellValue());
                    }
                } else {
                    return new BigDecimal(value);
                }
            }
        } catch (IllegalStateException e) {
            return null;
        }
    }

}
