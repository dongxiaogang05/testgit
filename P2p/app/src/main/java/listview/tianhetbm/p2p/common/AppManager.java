package listview.tianhetbm.p2p.common;

import android.app.Activity;

import java.util.Stack;

/**
 * @date:2020/9/7
 * @author:dongxiaogang
 * @description:
 */
public class AppManager {
    private Stack<Activity> activityStack=new Stack<>();
    public static AppManager appManager=null;
    private AppManager(){}
    public static AppManager getInstance(){
        if(appManager==null){
            appManager=new AppManager();
        }
        return appManager;
    }
    /**
     *栈中加入
     *@author:dongxiaogang 187188534@qq.com
     *create at 2020/9/7 17:35
     */
    public void addActivity(Activity activity){
        activityStack.add(activity);
    }
    /**
     *删除actvity
     *@author:dongxiaogang 187188534@qq.com
     *create at 2020/9/7 17:43
     */
    public void removeActivity(Activity activity){
        for(int i=activityStack.size()-1;i>=0;i--){
            Activity activity1=activityStack.get(i);
            if(activity1.getClass().equals(activity.getClass())){
                activity1.finish();
                activityStack.remove(activity1);
                break;
            }
        }
    }
    /**
     *删除当前activity
     *@author:dongxiaogang 187188534@qq.com
     *create at 2020/9/7 17:45
     */
    public void removeCurrentActivity(){
        Activity lastEmelent=activityStack.lastElement();
        lastEmelent.finish();
        activityStack.remove(lastEmelent);
    }
    /**
     *删除所有的activity
     *@author:dongxiaogang 187188534@qq.com
     *create at 2020/9/7 17:45
     */
    public void removeAll(){
        for(int i=activityStack.size()-1;i>=0;i--){
            Activity activity=activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }
    /**
     *返回栈中activity的数量
     *@author:dongxiaogang 187188534@qq.com
     *create at 2020/9/7 17:47
     */
    public int getSize(){
        return activityStack.size();
    }


}
