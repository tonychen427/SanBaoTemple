package com.foguangshan.dataservice;

import com.foguangshan.dataservice._core.JSONParser;
import com.foguangshan.dataservice._core.SoapEngine;
import com.foguangshan.modellibrary.Banner;
import com.foguangshan.modellibrary.Book;
import com.foguangshan.modellibrary.BookChapter;
import com.foguangshan.modellibrary.Component;
import com.foguangshan.modellibrary.NavMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.ksoap2.serialization.PropertyInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

/**
 * Created by MacBookPro on 4/8/15.
 */
public class BookService implements IBookService {

    private SoapEngine mSoapEngine;

    @Inject
    public BookService(){

    }

    @Override
    public int getServiceData() {
        return 3;
    }

    @Override
    public List<NavMenu> getNavMenuService() {

        List<NavMenu> myNavMenu = new ArrayList<NavMenu>();
        myNavMenu.add(new NavMenu(001,"Home"));

        return myNavMenu;
    }

    @Override
    public String getJson(String url) {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.getJSONFromUrl(url);
    }


    @Override
    public List<Banner> getBannerListService() {
        mSoapEngine = new SoapEngine("http://clientsbox.com/ws/sanbaotemple.asmx","GetAllSanBaoTempleBannersJSON",null);
        String Data  = mSoapEngine.getResult();
        Type listType = new TypeToken<ArrayList<Banner>>(){}.getType();
        List<Banner> myBannerList = new Gson().fromJson(Data,listType);
        return myBannerList;
    }


    public List<Book> getSanBaoTempleBooks (){

        mSoapEngine = new SoapEngine("http://clientsbox.com/ws/sanbaotemple.asmx","GetAllSanBaoTempleBooksJSON",null);
        String Data  = mSoapEngine.getResult();
        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
        List<Book> mBookList = new Gson().fromJson(Data,listType);
        return mBookList;
    }

    @Override
    public List<BookChapter> getBookChapterListService(int bookId) {

        List<PropertyInfo> mPropertyInfo = new ArrayList<PropertyInfo>();
        PropertyInfo mPi = new PropertyInfo();
        mPi.setName("bookId");
        mPi.setValue(bookId);
        mPi.setType(Integer.class);
        mPropertyInfo.add(mPi);

        mSoapEngine = new SoapEngine("http://clientsbox.com/ws/sanbaotemple.asmx","GetAllSanBaoTempleChaptersByBookIdJSON",mPropertyInfo);
        String Data  = mSoapEngine.getResult();
        Type listType = new TypeToken<ArrayList<BookChapter>>(){}.getType();
        List<BookChapter> mBookChapters = new Gson().fromJson(Data,listType);
        return mBookChapters;
    }
}
//        Do not delete this
//
//        mSoapEngine = new SoapEngine("http://clientsbox.com/ws/sanbaotemple.asmx","HelloWorld",null);
//        //String Data2 = mSoapEngine.getResult();
//
//
//        List<PropertyInfo> mPropertyInfo = new ArrayList<PropertyInfo>();
//        PropertyInfo mPi = new PropertyInfo();
//        mPi.setName("a");
//        mPi.setValue(4);
//        mPi.setType(Integer.class);
//        mPropertyInfo.add(mPi);
//        PropertyInfo mPi2 = new PropertyInfo();
//        mPi2.setName("b");
//        mPi2.setValue(3);
//        mPi2.setType(Integer.class);
//        mPropertyInfo.add(mPi2);
//
//        mSoapEngine = new SoapEngine("http://grasshoppernetwork.com/NewFile.asmx","Add",mPropertyInfo);
//
//        //String Data3 = mSoapEngine.getResult();
//
//        return new ArrayList<Book>();