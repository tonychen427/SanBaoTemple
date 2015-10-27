package com.foguangshan.modellibrary;

/**
 * Created by MacBookPro on 3/20/15.
 */
public class Banner {
    public Integer id;
    public String BannerTitle;
    public String BannerImageUrl;

    public Banner() {
    }

    public Banner(Integer id, String bannerTitle, String bannerImageUrl) {
        this.id = id;
        this.BannerTitle = bannerTitle;
        this.BannerImageUrl = bannerImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerTitle() {
        return BannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        BannerTitle = bannerTitle;
    }

    public String getBannerImageUrl() {
        return BannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        BannerImageUrl = bannerImageUrl;
    }
}
