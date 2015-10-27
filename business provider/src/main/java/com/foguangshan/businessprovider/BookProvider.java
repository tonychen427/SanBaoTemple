package com.foguangshan.businessprovider;

import com.foguangshan.dataservice.IBookService;
import com.foguangshan.modellibrary.Banner;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.modellibrary.BookChapter;
import com.foguangshan.modellibrary.NavMenu;

import java.util.ArrayList;
import java.util.List;
import static com.wagnerandade.coollection.Coollection.*;

import javax.inject.Inject;
/**
 * Created by MacBookPro on 4/8/15.
 */
public class BookProvider implements IBookProvider {
    private IBookService _bookService;

    @Inject
    public BookProvider(IBookService bookService){
        _bookService = bookService;
    }

    @Override
    public int getProviderData() {
        return _bookService.getServiceData();
    }

    @Override
    public List<NavMenu> getNavMenuProvider() {
        return _bookService.getNavMenuService();
    }

    @Override
    public String getJsonProvider(String url) {
        return _bookService.getJson(url);
    }

    @Override
    public List<Banner> getBannerListProvider() {
        return _bookService.getBannerListService();
    }

    @Override
    public List<Book> getSanBaoTempleBooks() {
        return _bookService.getSanBaoTempleBooks();
    }

    @Override
    public List<BookChapter> getBookChapterListProvider(int bookId) {
        return _bookService.getBookChapterListService(bookId);
    }
}
