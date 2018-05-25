package cn.lth.util;

public class ListSort_ {
    /**
     * 选着排序法
     *
     * @param x
     * @param n
     */
    public static void sel_sort(int [] x, int n){
        int k, i, m, t;
        for(k=0; k<n-1; k++){  // 多趟排序
            m = k;
            for(i=k+1; i<n; i++){
                if(x[i] < x[m])
                    m = i;
            }
            t = x[k];
            x[k] = x[m];
            x[m] = t;
        }
    }

    /**
     * 选择排序法-不同实现
     *
     * @param arr
     */
    public static void selectSort(int arr[]){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[i]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
