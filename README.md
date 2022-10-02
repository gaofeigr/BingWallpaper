# BingWallpaper

## Description

Mac每天自动更换系统壁纸为必应壁纸

## Usage

- 下载 BingWallpaper.jar

```shell
nohup java -jar /Users/gaofei/Pictures/BingWallpaper/bin/BingWallpaper.jar  > log.file  2>&1 &
```
执行上面的shell命令并将其添加到开机启动

- 设置定时任务定时拉取壁纸

```shell
crontab -e
5 0 */1 * * sh ~/Pictures/BingWallpaper/bin/BingWallpaper.sh
```
设置桌面壁纸文件夹为必应壁纸文件夹，并每日自动更新
