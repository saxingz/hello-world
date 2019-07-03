package org.saxing.a.algorithm2;

/**
 * leet code 69
 */
public class SqrtTest {

    public static void main(String[] args) {

        int res = mySqrt1(8);
        System.out.println(res);

        int res2 = mySqrt2(8);
        System.out.println(res2);


        int res3 = mySqrt22(16);
        System.out.println(res3);

    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    // 方案二 牛顿迭代法
    public static int mySqrt22(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = 1;
        while (res != last){
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt2(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x, res = 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (mid == x / mid){
                return mid;
            } else if (mid > x / mid){
                r = mid - 1;
            } else {
                l = mid + 1;
                res = l;
            }
        }
        return res;
    }

//==================================================================================================
//==================================================================================================
//==================================================================================================

    public static int mySqrt1(int x) {
        if (x == 0 || x == 1) return x;
        int l = 0, r = x, res = 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (mid == x / mid){
                return mid;
            } else if (mid > x / mid){
                r = mid - 1;
            } else {
                l = mid + 1;
                res = l;
            }
        }
        return res;
    }


}
