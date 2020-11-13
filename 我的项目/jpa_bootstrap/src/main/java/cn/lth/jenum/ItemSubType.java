package cn.lth.jenum;

/**
 *  专项巡视子类型
 *   0设备红外测温
 *   1油位油温表抄录
 *   2避雷器表计抄录
 *   3 SF6压力表抄录
 *   4液压表抄录
 *   5位置状态识别抄录
 */
public enum ItemSubType {
    ITEM_0(0, "设备红外测温"),
    ITEM_1(1, "油位油温表抄录"),
    ITEM_2(2, "避雷器表计抄录"),
    ITEM_3(3, "SF6压力表抄录"),
    ITEM_4(4, "液压表抄录"),
    ITEM_5(5, "位置状态识别抄录"),
    ;


    private int val;
    private String label;

    ItemSubType(int val, String label){
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
