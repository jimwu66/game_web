package jimwu.itest.portal.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class FieldValue {

    public <T> T getFieldValue(Object target, String fieldName, Class<T> typeName) {
        try {
            Object fieldValue = FieldUtils.readField(target, fieldName, true);
            return (T)fieldValue;
        } catch (IllegalAccessException e) {
            log.error("出错:实体类{}没有{}类型的{}属性字段!",target.getClass(),typeName.getSimpleName(),fieldName);
            throw new RuntimeException(e);
        }
    }
    public String getStringValue(Object target, String fieldName) {
        return getFieldValue(target,fieldName,String.class);
    }
}
