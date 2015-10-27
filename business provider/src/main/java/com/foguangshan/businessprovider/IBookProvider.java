package com.foguangshan.businessprovider;

import com.foguangshan.modellibrary.Banner;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.modellibrary.BookChapter;
import com.foguangshan.modellibrary.NavMenu;

import java.util.List;

/**
 * Created by MacBookPro on 4/8/15.
 */
public interface IBookProvider {
    public int getProviderData();
    public List<NavMenu> getNavMenuProvider();
    public String getJsonProvider (String url);

    public List<Banner> getBannerListProvider();
    public List<Book> getSanBaoTempleBooks();
    public List<BookChapter> getBookChapterListProvider(int bookId);
}
