# ReadHeadIDCardDemo

项目: 华视读取身份证信息演示Demo

## 项目介绍 ReadHeadIDCardDemo
1. 硬件依赖于 华视（用于身份证信息读取）
2. 实现身份证识别、包含身份证照片等数据。
3. 智慧办公识别等功能。

### 项目演示（在线视频显示）和照片演示

[点击这里打开视频演示](https://share-video.bj.bcebos.com/人脸识别与身份证读取.mov)

![人脸识别信息](https://raw.githubusercontent.com/lixiaodaoaaa/publicSharePic/master/%E4%BA%BA%E8%84%B8%E8%AF%86%E5%88%AB%E5%8F%96.png)

![](https://raw.githubusercontent.com/lixiaodaoaaa/publicSharePic/master/%E8%AF%BB%E5%8F%96%E8%BA%AB%E4%BB%BD%E8%AF%81-1.png)

## 开发事项
### ReadIDCardModel 读取照片崩溃
#### 解决方法：
 libwltdecode.so 目录文件夹为armeabi.更改为 armeabi-v7a
 更改原因：与FaceVerifyModel很多so包不能copy到apk中实现打包直接导致崩溃。


