package com.example.notifimapgalaxyfit;

import android.os.Bundle;

public class NotifiObj {

    private String title ;
    private String subText;
    private String showChronometer ;
    private int icon;
    private String text ;
    private String progress ;
    private String progressMax ;
    private String appInfo ;
    private String showWhen ;
    private String largeIcon;
    private String infoText ;
    private String progressIndeterminate ;
    private String remoteInputHistory ;
    private String colorized ;


    public NotifiObj setupDataFromBundle(Bundle bundle) throws Exception{

        NotifiObj notifiObj = new NotifiObj(
                bundle.getString("android.title"),
                bundle.getString("android.subText"),
                bundle.getString("android.showChronometer"),
                bundle.getInt("android.icon"),
                bundle.getString("android.text"),
                bundle.getString("android.progress"),
                bundle.getString("android.progressMax"),
                bundle.getString("android.appInfo"),
                bundle.getString("android.showWhen"),
                bundle.getString("android.largeIcon"),
                bundle.getString("android.infoText"),
                bundle.getString("android.progressIndeterminate"),
                bundle.getString("android.remoteInputHistory"),
                bundle.getString("android.colorized")



                );

        return notifiObj;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getShowChronometer() {
        return showChronometer;
    }

    public void setShowChronometer(String showChronometer) {
        this.showChronometer = showChronometer;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgressMax() {
        return progressMax;
    }

    public void setProgressMax(String progressMax) {
        this.progressMax = progressMax;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getShowWhen() {
        return showWhen;
    }

    public void setShowWhen(String showWhen) {
        this.showWhen = showWhen;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getProgressIndeterminate() {
        return progressIndeterminate;
    }

    public void setProgressIndeterminate(String progressIndeterminate) {
        this.progressIndeterminate = progressIndeterminate;
    }

    public String getRemoteInputHistory() {
        return remoteInputHistory;
    }

    public void setRemoteInputHistory(String remoteInputHistory) {
        this.remoteInputHistory = remoteInputHistory;
    }

    public String getColorized() {
        return colorized;
    }

    public void setColorized(String colorized) {
        this.colorized = colorized;
    }

    public NotifiObj() {
    }

    public NotifiObj(String title, String subText, String showChronometer, int icon, String text, String progress, String progressMax, String appInfo, String showWhen, String largeIcon, String infoText, String progressIndeterminate, String remoteInputHistory, String colorized) {
        this.title = title;
        this.subText = subText;
        this.showChronometer = showChronometer;
        this.icon = icon;
        this.text = text;
        this.progress = progress;
        this.progressMax = progressMax;
        this.appInfo = appInfo;
        this.showWhen = showWhen;
        this.largeIcon = largeIcon;
        this.infoText = infoText;
        this.progressIndeterminate = progressIndeterminate;
        this.remoteInputHistory = remoteInputHistory;
        this.colorized = colorized;
    }


}
