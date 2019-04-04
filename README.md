# 基于百度富文本UEditor独立服务

## 项目介绍
项目基于百度富文本编辑器ueditor，多人开发存在文件只能保存在本地的问题，开发时无法将上传文件统一保存，项目构建ueserver服务，接收客户端上传的文件

## 项目构成
- ueserver
- ueclient
- nginx文件代理
 
## 项目说明
- ueserver

此服务单独部署，作为接收文件的统一服务器，暂时支持保存在服务器文件系统中，后续如有需求将改造成支持保存文件到fastdfs


- ueclient

此服务为文件客户端，包含对ue前端的demo，文件通过ue插件上传，利用apache-fileupload构建fileinputstreamBody，利用httpclinet传输至ueserver

- nginx

nginx对上传路径做代理，ueserver通过配置返回图片路径为nginx代理路径+文件保存的路径
