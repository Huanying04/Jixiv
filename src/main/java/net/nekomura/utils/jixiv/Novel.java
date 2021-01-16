package net.nekomura.utils.jixiv;


import java.io.IOException;

public class Novel extends Artwork {

    public Novel(String phpSession) {
        super(phpSession);
    }

    public Novel(String phpSession, String userAgent) {
        super(phpSession, userAgent);
    }

    /**
     * 獲取小說作品之資訊物件
     * @param id 小說id
     * @return 小說作品之資訊物件
     * @throws IOException 讀取網路資料失敗
     */
    public NovelInfo get(int id) throws IOException {
        return this.getInfo(id);
    }

    /**
     * 獲取小說作品之資訊物件
     * @param id 小說id
     * @return 小說作品之資訊物件
     * @throws IOException 讀取網路資料失敗
     */
    public NovelInfo getInfo(int id) throws IOException {
        return super.getInfo(id).toNovelInfo();
    }
}