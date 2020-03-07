# ReadHeadIDCardDemo

项目: 华视读取身份证信息演示Demo


## 项目介绍 ReadHeadIDCardDemo
1. 硬件依赖于 华视（用于身份证信息读取）
2. 实现身份证识别、包含身份证照片等数据。
3. 智慧办公识别等功能。

### 项目演示

[https://share-video.bj.bcebos.com/人脸识别与身份证读取.mov](https://share-video.bj.bcebos.com/人脸识别与身份证读取.mov)

## 开发事项
### ReadIDCardModel 读取照片崩溃
#### 解决方法：
 libwltdecode.so 目录文件夹为armeabi.更改为 armeabi-v7a
 更改原因：与FaceVerifyModel很多so包不能copy到apk中实现打包直接导致崩溃。


