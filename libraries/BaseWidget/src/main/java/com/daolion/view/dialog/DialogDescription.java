package com.daolion.view.dialog;

/**
 * Created by lixiaodaoaaa on 2016/11/2.
 */

/**
 * dialog 的描述 类
 */
public class DialogDescription {

    private String title;
    private String dialogContent;
    private String positiveText;
    private String negativeText;

    public DialogDescription(String title, String dialogContent, String positiveText, String negativeText) {
        this.title = title;
        this.dialogContent = dialogContent;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
    }

    public DialogDescription(String title, String negativeText, String positiveText) {
        this.title = title;
        this.negativeText = negativeText;
        this.positiveText = positiveText;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDialogContent() {
        return dialogContent;
    }

    public void setDialogContent(String dialogContent) {
        this.dialogContent = dialogContent;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }
}
