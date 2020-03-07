# SmartIdAuth
项目:人脸认证一体机

#### 项目介绍 SmartIdAuth

## 介绍
1. 关于智慧案场防飞单风控管理。
2. 实现身份证识别、用户系统信息上报。
3. 智慧办公识别等功能。
4. 销售与用户一体机（智能识别系统）。

## 开发事项

### ReadIDCardModel 读取照片崩溃
### 解决方法：

 libwltdecode.so 目录文件夹为armeabi.更改为 armeabi-v7a

 更改原因：与FaceVerifyModel很多so包不能copy到apk中实现打包直接导致崩溃。


