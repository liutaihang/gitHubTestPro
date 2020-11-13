package cn.lth.util.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * excel字段属性类
 * @ClassName: ExcelModel
 * @Description: TODO
 * @author dh
 * @date 2020年6月8日
 *
 */
public class ExcelModel {
    /**取值字段的名称*/
    private String fieldName;

    /**是否需要翻译成自定义的值*/
    private boolean needtranslate=false;

    /**需要翻译的值的映射*/
    private Map<Object,Object> translateMappingInfo = new HashMap<>();

    /**是否是图片*/
    private boolean isPicture;
    
    /**上传excel时如果有图片则需要保存，这需要填此属性，进行下载*/
    private String imageDownPath;

    public ExcelModel(String fieldName, boolean needtranslate, Map<Object, Object> translateMappingInfo, boolean isPicture) {
        this.fieldName = fieldName;
        this.needtranslate = needtranslate;
        this.translateMappingInfo = translateMappingInfo;
        this.isPicture = isPicture;
    }
    
    
    
    public ExcelModel(String fieldName, boolean needtranslate, Map<Object, Object> translateMappingInfo,
			boolean isPicture, String imageDownPath) {
		super();
		this.fieldName = fieldName;
		this.needtranslate = needtranslate;
		this.translateMappingInfo = translateMappingInfo;
		this.isPicture = isPicture;
		this.imageDownPath = imageDownPath;
	}



	public void setImageDownPath(String imageDownPath) {
    	this.imageDownPath=imageDownPath;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isNeedtranslate() {
        return needtranslate;
    }

    public void setNeedtranslate(boolean needtranslate) {
        this.needtranslate = needtranslate;
    }

    public Map<Object, Object> getTranslateMappingInfo() {
        return translateMappingInfo;
    }

    public void setTranslateMappingInfo(Map<Object, Object> translateMappingInfo) {
        this.translateMappingInfo = translateMappingInfo;
    }

    public boolean isPicture() {
        return isPicture;
    }

    public void setPicture(boolean picture) {
        isPicture = picture;
    }
}
