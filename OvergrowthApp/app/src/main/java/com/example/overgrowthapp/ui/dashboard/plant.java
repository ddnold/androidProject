package com.example.overgrowthapp.ui.dashboard;

public class plant {

    String CommonId;
    String BotanicalID;
    String imgSrc;
    String COMMONID; // Used for search
    String Family;
    String SoilPH;
    String Soil;
    String BloomTime;
    String HardinessZone;
    String Type;
    String url;
    String Sun;
    String NativeArea;
    String Color;
    String Size;
    String Toxicity;
    String Water;
    String BloomColor;
    String GrowingTime;

    public String getBloomColor() {
        return BloomColor;
    }

    public String getGrowingTime() {
        return GrowingTime;
    }

    public String getFamily() {
        return Family;
    }

    public String getSoilPH() {
        return SoilPH;
    }

    public String getSoil() {
        return Soil;
    }

    public String getBloomTime() {
        return BloomTime;
    }

    public String getHardinessZone() {
        return HardinessZone;
    }

    public String getType() {
        return Type;
    }

    public String getUrl() {
        return url;
    }

    public String getSun() {
        return Sun;
    }

    public String getNativeArea() {
        return NativeArea;
    }

    public String getColor() {
        return Color;
    }

    public String getSize() {
        return Size;
    }

    public String getToxicity() {
        return Toxicity;
    }

    public String getWater() {
        return Water;
    }



    public String getCommonIDCAPS() {return COMMONID; }

    public String getCommonID() {
        return CommonId;
    }

    public String getBotanicalID() {
        return BotanicalID;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
