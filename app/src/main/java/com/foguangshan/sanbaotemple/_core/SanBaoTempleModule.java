package com.foguangshan.sanbaotemple._core;

import com.foguangshan.businessprovider.BookProvider;
import com.foguangshan.businessprovider.IBookProvider;
import com.foguangshan.dataservice.BookService;
import com.foguangshan.dataservice.IBookService;


import dagger.Module;
import dagger.Provides;

@Module
public class SanBaoTempleModule {

    public SanBaoTempleModule() {

    }

    @Provides
    IBookProvider bookProvider(BookProvider dp) { return  dp;}

    @Provides
    IBookService bookService(BookService bs) { return  bs;}
}
