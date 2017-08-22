package com.example.free.mymvpdemo.util.HttpUtils;

public class FormImage {

    private String mName;  //参数名称
    private String mFileName;  //文件名称
    private String filepath;   //文件路径

    public FormImage() {
    }


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    @Override
    public String toString() {
        return "FormImage{" +
                "mName='" + mName + '\'' +
                ", mFileName='" + mFileName + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
