package cn.lth.jenum;

/**
 * 定义联合巡检任务类型
 */
public enum PatrolCombineTaskType {

    UNKNOWN(-1,""),
    SELFDEFINE(0, "自定义"),
    ALL(1, "全面巡视"),
    ROUTINE (1, "例行巡视"),
    ITEM (1, "专项巡视"),
    SPECIAL (1, "特殊巡视"),
    NIGHT  (1, "熄灯巡视"),


    ;

    private int val;
    private String label;

    PatrolCombineTaskType(int val, String label){
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
