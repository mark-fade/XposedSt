package com.example.dell.xposedst;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainXp implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("包名-------------:" + lpparam.packageName);
        if (lpparam.packageName.equals("com.example.sample")) {
            XposedHelpers.findAndHookMethod("com.example.sample.ui.backanalysis.BackAnalysisActivity"
                    , lpparam.classLoader
                    , "onCreate"
                    , Bundle.class
                    , new XC_MethodHook() { //3.1.4
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Exception {
                            Class c = param.thisObject.getClass();
                            XposedBridge.log("class name-------------:" + c.getName());
                            Field field = c.getField("tv_test");
                            TextView tv = (TextView) field.get(param.thisObject);
                            tv.setText("xposed ： 修改后 666666");
                        }
                    }
            );

        }
    }
}
