# jixiv
用來實現pixiv爬蟲等的Java框架

![jixiv](https://github.com/Huanying04/jixiv/blob/master/image/jixiv.png)
# 功能
* 獲取插畫/漫畫信息
* 獲取小說信息
* 下載插畫
* 下載動圖(zip 內含動圖所有影格)
* 下載漫畫
* 下載畫師所有作品
* 下載小說封面
* 獲取小說內容
* 排行榜
* 搜尋
* etc.
# 配置方法
(尚無，將會在完善後公開至Maven Central)

(可暫時用jar代替)
# 使用方法
## PHPSESSID
由於pixiv防爬蟲防得很嚴或是我的程式能力很弱，目前做不出帳號密碼模擬登入。可暫時藉由PHPSESSID來模擬登入。

![phpsession](https://github.com/Huanying04/Huanying04/blob/main/phpsession.png)

在瀏覽器中找到pixiv cookie中的PHPSESSID即可用之來模擬登入。
## 獲取插畫/漫畫信息
```java
String phpSession = ""; //pixiv登入後cookie裡的PHPSESSID
String userAgent = "";  //你的User-Agent，如果沒有的話將會隨機生成
int id = 85209753; //插畫id
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);
PixivIllustrationInfo iInfo = pi.get(id);
//獲取插畫標題
String title = iInfo.getTitle();
//獲取插畫簡介
String description = iInfo.getRawDescription();
//獲取插畫所有標籤名字
String[] tags = iInfo.getTags();
//獲取插畫頁數
int pageCount = iInfo.getPageCount();
//獲取觀看次數
int viewCount = iInfo.getViewCount();
//獲取收藏次數
int bookmarkCount = iInfo.getBookmarkCount();
//獲取讚數
int likeCount = iInfo.getLikeCount();
//獲取評論數
int commemtCount = iInfo.getCommentCount();
```
## 獲取小說信息
```java
int id = 11387000; //小說id
PixivNovel pn = new PixivNovel(phpSession, userAgent);
PixivNovelInfo nInfo = pn.get(id);
//獲取小說標題
String title = nInfo.getTitle();
//獲取小說簡介
String description = nInfo.getRawDescription();
//獲取小說所有標籤名字
String[] tags = nInfo.getTags();
//獲取頁數
int pageCount = nInfo.getPageCount();
//獲取總字數
int textCount = nInfo.getTextCount();
//獲取觀看次數
int viewCount = nInfo.getViewCount();
//獲取收藏次數
int bookmarkCount = nInfo.getBookmarkCount();
//獲取讚數
int likeCount = nInfo.getLikeCount();
//獲取評論數
int commemtCount = nInfo.getCommentCount();
```
## 下載插畫/漫畫
### 指定頁數
#### 完整版
可以指定第幾頁及插畫大小。
```java
String path = "";  //儲存位置
int id = 85209753;  //插畫id
int page = 0;  //頁碼
PixivImageSize size = PixivImageSize.Original;  //圖片大小
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);
PixivIllustrationInfo iInfo = pi.get(id);
iInfo.download(path, page, size);  //將id為85209753的插畫的第0頁下載到path
```
#### 簡易版
只需id就足以完成
```java
String path = "";  //儲存位置
int id = 85209753;  //插畫id
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);
PixivIllustrationInfo iInfo = pi.get(id);
iInfo.download(path);  //將id為85209753的插畫的第0頁下載到path
```
### 所有頁數
```java
String path = "";  //資料夾位置
int id = 85207001;  //插畫id
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);
PixivIllustrationInfo iInfo = pi.get(id);
iInfo.downloadAll(path, PixivImageSize.Original);  //將id中的所有插畫都下載到path裡
```
## 下載使用者所有插畫
```java
String path = "";  //資料夾位置
int userId = 5445450;  //使用者id
PixivImageSize size = PixivImageSize.Original;  //圖片大小
Pixiv p = new Pixiv(phpSession, userAgent);
p.downloadUserAll(path, userId, size);  //將使用者id中的所有插畫及漫畫都下載到path裡
```
## 下載動圖(zip)
```java
String path = "";  //資料夾位置
int id = 44298467;  //插畫id
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);
PixivIllustrationInfo iInfo = pi.get(id);
iInfo.downloadUgoiraZip(path);
```
## 獲取小說內容
```java
int id = 11387000; //小說id
PixivNovel pn = new PixivNovel(phpSession, userAgent);
PixivNovelInfo nInfo = pn.get(id);
String content = nInfo.getContent();  //小說內容
```
## 下載小說封面
```java
String path = "";  //儲存位置
int id = 11387000; //小說id
PixivNovel pn = new PixivNovel(phpSession, userAgent);
PixivNovelInfo nInfo = pn.get(id);
pn.downloadCover(path);
```
## 作品是否為R-18或R-18G
如果要判斷作品為成人限制作品，可使用下面方法判斷。
```java
PixivArtworkInfo.isNSFW();
```
範例：
```java
PixivIllustration pi = new PixivIllustration(phpSession, userAgent);  //插畫或漫畫
PixivIllustrationInfo iInfo = pi.get(id);

PixivNovel pn = new PixivNovel(phpSession, userAgent);  //小說
PixivNovelInfo nInfo = pn.get(id);

iInfo.isNSFW(id);  //作品(插畫或漫畫)id是否為成人限制
nInfo.isNSFW(id);  //作品(小說)id是否為成人限制
```
如果只要判斷R-18或R-18G則為
```java
PixivArtworkInfo.isR18();  //作品id是否為R-18
PixivArtworkInfo.isR18G();  //作品id是否為R-18-G
```
## 排行榜
可使用下面的方式獲取今日綜合排行榜
```java
Pixiv p = new Pixiv(phpSession);
int page = 1;  //頁碼
PixivRank pr = p.rank(page);
```
或是使用下列方法獲取指定排行榜
```java
Pixiv p = new Pixiv(phpSession, userAgent);
int page = 1;  //頁碼
PixivRankMode mode = PixivRankMode.Daily;  //排行榜類別
PixivRankContent content = PixivRankContent.Illust;  //作品形式
String date = "20201001";  //日期
PixivRank pr = p.rank(page, mode, content, date);
```
## 搜尋
可使用下面方法關鍵字搜尋插畫。
```java
Pixiv p = new Pixiv(phpSession);
String keyword = "";  //關鍵字
int page = 1;  //頁碼
PixivSearchResult result = p.search(keyword, page);
```
或是使用下面方法搜尋。
```java
Pixiv p = new Pixiv(phpSession, userAgent);
String keyword = "";  //關鍵字
int page = 1;  //頁碼
PixivSearchArtistType artistType = PixivSearchArtistType.Illustrations;  //搜尋作品類別
PixivSearchOrder order = PixivSearchOrder.NEW_TO_OLD;  //排序方式
PixivSearchMode mode = PixivSearchMode.ALL;  //搜尋作品年齡分類
PixivSearchSMode sMode = PixivSearchSMode.S_TAG;  //關鍵字搜尋方式
PixivSearchType type = PixivSearchType.Illust;  //搜尋作品類別
PixivSearchResult result = p.search(keyword, page, artistType, order, mode, sMode, type);
```
