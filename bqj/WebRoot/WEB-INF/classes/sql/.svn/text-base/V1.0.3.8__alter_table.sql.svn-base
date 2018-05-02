--为视频表增加视频封面图片ID字段
ALTER TABLE `p_video`
ADD COLUMN `VideoImgID`  varchar(50) NULL COMMENT '视频封面图片id' AFTER `regionId`;

--为墙报表增加图片原名字段
ALTER TABLE `p_silhouette`
ADD COLUMN `Name`  varchar(255) NULL COMMENT '图片文件名称' AFTER `status`;