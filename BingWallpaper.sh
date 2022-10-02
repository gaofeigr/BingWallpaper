#!/bin/sh
localDir="/Users/$USER/Pictures/BingWallpaper"
localHistoryDir="/Users/$USER/Pictures/BingWallpaper/history"
filenameRegex=".*"$(date "+%Y-%m-%d")".*jpg"

if [ ! -d "$localDir" ]; then
    mkdir "$localDir"
fi
if [ ! -d "$localHistoryDir" ]; then
    mkdir "$localHistoryDir"
fi
mv $localDir/*.jpg $localHistoryDir
imgurl=$(curl -L "http://localhost:9999/bingWallpaper/getImageUrl")
echo img $imgurl
filename=$(curl -L "http://localhost:9999/bingWallpaper/getFileName")
echo filename $filename
localpath="$localDir/$filename"
curl -o $localpath -H 'Cache-Control: no-cache' $imgurl
#osascript -e "                              \
#    tell application \"System Events\" to   \
#        tell every desktop to               \
#            set picture to \"$localpath\""
