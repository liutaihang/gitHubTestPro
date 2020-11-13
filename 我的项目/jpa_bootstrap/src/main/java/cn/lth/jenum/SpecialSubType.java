package cn.lth.jenum;

/**
 * 恶劣天气特殊巡视子类型：
 * 0 大风
 * 1 雷暴
 * 2 雾霾（含毛毛雨、大雾等）
 * 3 雨后
 * 4 下雪
 * 5 气温骤变（含低温天气）
 * 6 高温
 * 7 冰雹
 * 8 覆冰
 * 9 沙尘暴
 */
public enum  SpecialSubType {
    SPECIAL_0(0, "大风"),
    SPECIAL_1(1, "雷暴"),
    SPECIAL_2(2, "雾霾（含毛毛雨、大雾等）"),
    SPECIAL_3(3, "雨后"),
    SPECIAL_4(4, "下雪"),
    SPECIAL_5(5, "气温骤变（含低温天气）"),
    SPECIAL_6(6, "高温"),
    SPECIAL_7(7, "冰雹"),
    SPECIAL_8(8, "覆冰"),
    SPECIAL_9(9, "沙尘暴"),
    ;

    private int val;
    private String label;

    SpecialSubType(int val, String label){
        this.val = val;
        this.label = label;
    }

    public int getVal() {
        return val;
    }

    public String getLabel() {
        return label;
    }
}
