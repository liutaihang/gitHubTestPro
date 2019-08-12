package com.tw.liu.constructpro.JsonUtils;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class SummaryUtils {

    public Object converTo(Object object, Class clz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object o = Class.forName(clz.getName()).newInstance();
        List<Field> fields = Lists.newArrayList(clz.getDeclaredFields());

        Class superclass = clz.getSuperclass();
        List<Field> sp = Lists.newArrayList(superclass.getDeclaredFields());
        fields.addAll(sp);

        fields.stream().forEach(tem -> {
            tem.setAccessible(true);
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(tem.getName(), clz);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, CreationUtils.invokeGetter(tem.getName(), object, object.getClass()));
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        return o;
    }

}
