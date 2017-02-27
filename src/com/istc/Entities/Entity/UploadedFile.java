package com.istc.Entities.Entity;

/**
 * Created by lurui on 2017/2/2 0002.
 */

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Used to represent and manage uploaded files
 */
@Entity
public class UploadedFile implements Serializable{
    @Id
    private int fileID;
    private String fileName;
    /**文件扩展名used to save filename extension;*/
    private String fileExtention;

    private int requiredAuthority;
    private String fileCanonicalPath;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar uploadTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_member")
    private Member fileOwner;

    @Version
    int fileVersion;

    public UploadedFile() {
    }

    public UploadedFile(File file, Integer fileID, Member owner)throws IOException{
        if(file == null)return;
        this.fileID = fileID;
        this.fileCanonicalPath = file.getCanonicalPath();
        this.fileName = file.getName();
        this.fileExtention = fileName.substring(fileName.lastIndexOf('.'));
        this.fileOwner = owner;
        this.uploadTime = Calendar.getInstance();
    }

    public UploadedFile(File file, Integer fileID, Member fileOwner, Integer requiredAuthority) throws IOException{
        this(file, fileID, fileOwner);
        this.requiredAuthority = requiredAuthority;
    }

    /**
     * 根据文件绝对路径创建文件接口
     * @return null 如果 fileCanonicalPath 为 null
     * @return File 如果 fileCanonicalPath 存在且成功创建了文件
     */
    public File createFile(){
        return this.fileCanonicalPath == null ? null : new File(this.fileCanonicalPath);
    }

    public Calendar getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Calendar uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtention() {
        return fileExtention;
    }

    public void setFileExtention(String fileExtention) {
        this.fileExtention = fileExtention;
    }

    public int getRequiredAuthority() {
        return requiredAuthority;
    }

    public void setRequiredAuthority(int requiredAuthority) {
        this.requiredAuthority = requiredAuthority;
    }

    public String getFileCanonicalPath() {
        return fileCanonicalPath;
    }

    public void setFileCanonicalPath(String fileCanonicalPath) {
        this.fileCanonicalPath = fileCanonicalPath;
    }

    public int getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(int file_version) {
        this.fileVersion = file_version;
    }

    public Member getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(Member fileOwner) {
        this.fileOwner = fileOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadedFile)) return false;

        UploadedFile that = (UploadedFile) o;

        if (fileID != that.fileID) return false;
        if (requiredAuthority != that.requiredAuthority) return false;
        if (fileVersion != that.fileVersion) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (fileExtention != null ? !fileExtention.equals(that.fileExtention) : that.fileExtention != null)
            return false;
        if (fileCanonicalPath != null ? !fileCanonicalPath.equals(that.fileCanonicalPath) : that.fileCanonicalPath != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        return fileOwner != null ? fileOwner.equals(that.fileOwner) : that.fileOwner == null;

    }

    @Override
    public int hashCode() {
        int result = fileID;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (fileExtention != null ? fileExtention.hashCode() : 0);
        result = 31 * result + requiredAuthority;
        result = 31 * result + (fileCanonicalPath != null ? fileCanonicalPath.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (fileOwner != null ? fileOwner.hashCode() : 0);
        result = 31 * result + fileVersion;
        return result;
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "fileID=" + fileID +
                ", fileName='" + fileName + '\'' +
                ", fileExtention='" + fileExtention + '\'' +
                ", requiredAuthority=" + requiredAuthority +
                ", fileCanonicalPath='" + fileCanonicalPath + '\'' +
                ", uploadTime=" + uploadTime +
                ", fileOwner=" + fileOwner +
                ", fileVersion=" + fileVersion +
                '}';
    }
}
