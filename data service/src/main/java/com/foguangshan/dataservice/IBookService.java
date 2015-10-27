package com.foguangshan.dataservice;

import com.foguangshan.modellibrary.Banner;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.modellibrary.BookChapter;
import com.foguangshan.modellibrary.NavMenu;

import java.util.List;

/**
 * Created by MacBookPro on 4/8/15.
 */
public interface IBookService {

    public int getServiceData();
    public List<NavMenu> getNavMenuService();
    public String getJson(String url);

    public List<Banner> getBannerListService();

    public List<Book> getSanBaoTempleBooks();

    public List<BookChapter> getBookChapterListService(int bookId);


}
