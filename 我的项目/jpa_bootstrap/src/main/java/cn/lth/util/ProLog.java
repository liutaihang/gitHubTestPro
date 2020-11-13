package cn.lth.util;

import java.lang.annotation.*;

/**
 * @Title: jpa_bootstrap
 * @Package cn.lth.util
 * @Description: ${todo}
 * @author liutaihang
 * @date 2018/6/14 15:35
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProLog {
    enum Type{
        DEFAULT("默认"), SPECI("特殊"), NORMAL("一般");

        private String name;
        Type(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    String name () default "";
    String value() default "value";
    Type logType() default Type.DEFAULT;
}
