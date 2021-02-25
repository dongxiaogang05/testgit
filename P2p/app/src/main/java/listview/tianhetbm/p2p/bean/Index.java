package listview.tianhetbm.p2p.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @date:2020/9/14
 * @author:dongxiaogang
 * @description:
 */
public class Index implements Serializable {

    /**
     * id : 1
     * memberNum : 100
     * minTouMoney : 100
     * money : 10
     * name : 超级新手计划
     * progress : 90
     * suodingDays : 30
     * yearLv : 8.00
     */

    public ProInfo proInfo;
    /**
     * ID : 1
     * IMAPAURL : http://gwop.xtrich.com/xtApp/lexianghuo1.html
     * IMAURL : http://127.0.0.1:8080/sj/images/index01.jpg
     */

    public List<ImageArr> imageArr;

    public static class ProInfo {
        public String id;
        public String memberNum;
        public String minTouMoney;
        public String money;
        public String name;
        public String progress;
        public String suodingDays;
        public String yearLv;
    }

    public static class ImageArr {
        public String ID;
        public String IMAPAURL;
        public String IMAURL;
    }
}
